package com.lcw;

import com.lcw.one.modules.sys.entity.Dict;
import com.lcw.one.modules.sys.service.DictService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = OneApplication.class)
@WebAppConfiguration
public class OneApplicationTests {

	@Autowired
	private DictService dictService;

	@Test
	public void testFindAllList() throws Exception {
		List<Dict> dictList = dictService.findAllList();
		for (Dict dict : dictList) {
			System.out.println(dict.getId());
		}
	}

}
