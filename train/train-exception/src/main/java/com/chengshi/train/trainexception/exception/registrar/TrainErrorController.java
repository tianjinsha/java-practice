package com.chengshi.train.trainexception.exception.registrar;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TrainErrorController {
    /**
     * 404 error
     *
     * @return
     */
    @RequestMapping("/404")
    public String error404() {
        return "error/404";
    }

    /**
     * 403 error
     *
     * @return
     */
    @RequestMapping("/403")
    public String error403() {
        return "error/403";
    }

    /**
     * 500 error
     *
     * @return
     */
    @RequestMapping("/500")
    public String error500() {
        return "error/500";
    }
}
