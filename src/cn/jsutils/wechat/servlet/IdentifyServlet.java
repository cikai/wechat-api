package cn.jsutils.wechat.servlet;

import cn.jsutils.wechat.utils.Constant;
import org.apache.commons.codec.digest.DigestUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by cikai on 16-10-22.
 */
public class IdentifyServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");

        ArrayList<String> arrayList=new ArrayList<String>();
        arrayList.add(Constant.TOKEN);
        arrayList.add(timestamp);
        arrayList.add(nonce);
        Collections.sort(arrayList);
        String signStr = DigestUtils.shaHex(arrayList.get(0)+arrayList.get(1)+arrayList.get(2));

        PrintWriter out = response.getWriter();
        if (signStr.equals(signature)) {
            out.print(echostr);
        }
        out.close();
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            doGet(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}