package qnu.edu.vn.MXH.service;

import java.util.List;

import org.springframework.stereotype.Service;

import qnu.edu.vn.MXH.model.Post;
import qnu.edu.vn.MXH.repository.PostRepository;

@Service
public class PostService {
	private PostRepository pr=new PostRepository();
	public List<Post> search(String keyword){
		return pr.findByTitleOrBodyContainingIgnoreCase(keyword);
	}

}
