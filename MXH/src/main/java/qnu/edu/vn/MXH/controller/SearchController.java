package qnu.edu.vn.MXH.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import qnu.edu.vn.MXH.model.Post;
import qnu.edu.vn.MXH.service.PostService;

@Controller
@RequestMapping("/search")
public class SearchController {

    @Autowired
    private PostService ps;

    @GetMapping
    public String search(
            @RequestParam(value = "keyword", required = false) String keyword,
            Model model) {

        // CHỈ search khi có keyword
        if (keyword != null && !keyword.trim().isEmpty()) {
            List<Post> results = ps.search(keyword);
            model.addAttribute("posts", results);
            model.addAttribute("keyword", keyword);
        }

        return "search";
    }

    @GetMapping("/test")
    @ResponseBody
    public String test() {
        return "WORKING";
    }
}