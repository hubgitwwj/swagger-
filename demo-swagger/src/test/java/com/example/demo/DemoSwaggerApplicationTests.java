package com.example.demo;

import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import io.github.robwin.markup.builder.MarkupLanguage;
import io.github.robwin.swagger2markup.GroupBy;
import io.github.robwin.swagger2markup.Swagger2MarkupConverter;
import springfox.documentation.staticdocs.SwaggerResultHandler;

@AutoConfigureMockMvc
@AutoConfigureRestDocs(outputDir = "target/generated-snippets")
@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoSwaggerApplicationTests {

	private String snippetDir = "target/generated-snippets";
    private String outputDir  = "target/asciidoc";
    @Autowired
    private MockMvc mockMvc;
    
    /**
	 * web项目上下文
	 */
	@Autowired
	private WebApplicationContext webApplicationContext;


	/**
	 * 所有测试方法执行之前执行该方法
	 */
	@Before
	public void before() {
		//获取mockmvc对象实例
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
    
    @After
    public void Test() throws Exception {
	// 得到swagger.json,写入outputDir目录中
	    mockMvc.perform(get("/v2/api-docs").accept(MediaType.APPLICATION_JSON))
	            .andDo(SwaggerResultHandler.outputDirectory(outputDir).build())
	            .andExpect(status().isOk())
	            .andReturn();
	    // 读取上一步生成的swagger.json转成asciiDoc,写入到outputDir
	    // 这个outputDir必须和插件里面<generated></generated>标签配置一致
	    Swagger2MarkupConverter.from(outputDir + "/swagger.json")
	            .withPathsGroupedBy(GroupBy.TAGS)// 按tag排序
	            .withMarkupLanguage(MarkupLanguage.ASCIIDOC)// 格式
	            .withExamples(snippetDir)
	            .build()
	            .intoFolder(outputDir);// 输出
	}
	@Test
	public void TestApi() throws Exception {
	  
	}


}

