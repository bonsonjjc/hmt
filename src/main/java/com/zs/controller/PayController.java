package com.zs.controller;

import com.zs.config.Code;
import com.zs.config.Config;
import com.zs.comm.Respond;
import com.zs.model.PayInfo;
import com.zs.server.PayServer;
import com.zs.vo.Pay;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;
import java.util.Map;

@Controller
@RequestMapping("/pay")
public class PayController {
    @Autowired
    private PayServer payServer;

    @PostMapping("/pay/{payType}")
    @ResponseBody
    public Respond pay(@PathVariable("payType") String payType, Pay pay) {
        PayInfo payInfo = (PayInfo) SecurityUtils.getSubject().getSession().getAttribute("config");
        try {
            Respond respond = new Respond();
            if ("qrcode".equals(payType)) {
                Map map = payServer.qrcodePay(pay, payInfo);
            } else if ("barcode".equals(payType)) {
                Map map = payServer.barcodePay(pay, payInfo);
            } else if ("bank".equals(payType)) {
                String result = payServer.bankPay(pay, payInfo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Respond.error(Code.NO_EXITS);
    }

    @PostMapping("/refund")
    @ResponseBody
    public Respond refund(String fno, String money) {
        int refund = payServer.refund(fno, money);
        if (refund == 0) {
            return Respond.success("退款成功");
        }
        return Respond.success("退款失败");
    }

    public Respond sync(String fno) {
        PayInfo payInfo = (PayInfo) SecurityUtils.getSubject().getSession().getAttribute("config");
        payServer.sync(fno, payInfo);
        return Respond.success();
    }

    @PostMapping("/validate")
    @ResponseBody
    public Respond validate() {
        return Respond.success("");
    }
}
