package com.zs.server;

import com.zs.config.Config;
import com.zs.comm.GsonUtil;
import com.zs.comm.HttpUtil;
import com.zs.mapper.OrderMapper;
import com.zs.mapper.PayMapper;
import com.zs.model.Order;
import com.zs.model.PayInfo;
import com.zs.vo.Pay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class PayServer {

    @Autowired
    private PayMapper payMapper;
    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private Config config;

    public Map<String, Object> qrcodePay(Pay pay, PayInfo payInfo) throws Exception {
        pay.setTask("001");
        pay.setFeid(payInfo.getFeid());
        pay.setFstoreid(payInfo.getFsid());
        pay.setFeuser(payInfo.getFuid());

        if ("1".equals(pay.getFpaytype())) {
            pay.setFline(payInfo.getAli());
        } else if ("2".equals(pay.getFpaytype())) {
            pay.setFline(payInfo.getWx());
        } else if ("3".equals(pay.getFpaytype())) {
            pay.setFline(payInfo.getYsf());
        }
        Map<String, Object> param = GsonUtil.toMap(pay);
        // 发送请求
        String respond = HttpUtil.post(config.getPayUrl(), param, null);
        return GsonUtil.fromJson(respond);
    }

    private static boolean isWxBarcode(String barcode) {
        String[] wxCode = {"10", "11", "12", "13", "14", "15"};
        for (String flag : wxCode) {
            boolean b = barcode.startsWith(flag);
            if (b) return true;
        }
        return false;
    }

    public Map<String, Object> barcodePay(Pay pay, PayInfo payInfo) throws Exception {
        pay.setTask("008");
        pay.setFeid(payInfo.getFeid());
        pay.setFstoreid(payInfo.getFsid());
        pay.setFeuser(payInfo.getFuid());

        String barcode = pay.getBarcode();
        if (barcode.startsWith("28")) {
            pay.setFline(payInfo.getAli());
            pay.setFpaytype("1");
        } else if (isWxBarcode(barcode)) {
            pay.setFline(payInfo.getWx());
            pay.setFpaytype("2");
        } else if (barcode.startsWith("62")) {
            if (!StringUtils.isEmpty(payInfo.getYsf())) {
                pay.setFline(payInfo.getYsf());
            }
            pay.setFpaytype("5");
        }
        //发送请求
        if ("63".equals(pay.getBarcode())) {
            pay.setFline(payInfo.getAli());
        } else if ("34".equals(pay.getBarcode())) {
            pay.setFline(payInfo.getWx());
        } else if ("12".equals(pay.getBarcode())) {
            pay.setFline(payInfo.getYsf());
        }
        Map<String, Object> param = GsonUtil.toMap(pay);
        // 发送请求
        String respond = HttpUtil.post(config.getPayUrl(), param, null);
        return GsonUtil.fromJson(respond);
    }

    public String bankPay(Pay pay, PayInfo payInfo) {
        String line = payInfo.getBank();
        Order order = new Order();
        order.setFeid(payInfo.getFeid());
        order.setFsid(payInfo.getFsid());
        order.setFeuser(payInfo.getFuid());
        order.setFpaytype("4");
        order.setFstate(0);
        order.setFline(line);
        order.setFmoney(pay.getFmoney());
        if ("".equals(payInfo.getBank())) {
            order.setFline("2");
            order.setFdid("35");
        } else {
            order.setFline(line);
            order.setFdid(line.equals("2") ? "35" : line.equals("8") ? "80" : "122");
        }
        orderMapper.add(order);
        return order.getFresult();
    }

    public int refund(String fno, String money) {
        Order order = orderMapper.findByIdOrFno("", fno);
        if (order == null) {
            return -1;
        }
        Map<String, String> param = new HashMap<>();

        return 0;
    }

    public String sync(String fno, PayInfo payInfo) {
        Map<String, Object> map = new HashMap<>();
        map.put("feid", payInfo.getFeid());
        map.put("fsid", payInfo.getFsid());
        map.put("fno", fno);
        try {
            return HttpUtil.post(config.getPayUrl(), map, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
