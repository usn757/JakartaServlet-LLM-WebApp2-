package org.example.servletpreview.controller;

import io.github.cdimascio.dotenv.Dotenv;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;

import java.util.logging.Logger;

public abstract class Controller extends HttpServlet {

    Logger logger;


    abstract void initLogger();

    final Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();

    @Override
    public void init(ServletConfig config) throws ServletException { // init() 메서드 오버라이드
        super.init(config); // super.init(config) 호출 (필수!)
        initLogger(); // initLogger() 호출: logger 초기화
    }
}
