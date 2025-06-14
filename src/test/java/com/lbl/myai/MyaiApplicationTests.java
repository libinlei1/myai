package com.lbl.myai;

import org.junit.jupiter.api.Test;
import org.springframework.ai.reader.ExtractedTextFormatter;
import org.springframework.ai.reader.pdf.PagePdfDocumentReader;
import org.springframework.ai.reader.pdf.config.PdfDocumentReaderConfig;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.ai.document.Document;

import java.util.List;

@SpringBootTest
class MyaiApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	private VectorStore vectorStore;


	@Test
	public void testVectorStore(){
		Resource resource = new FileSystemResource("中二知识笔记.pdf");
		// 1.创建PDF的读取器
		PagePdfDocumentReader reader = new PagePdfDocumentReader(
				resource, // 文件源
				PdfDocumentReaderConfig.builder()
						.withPageExtractedTextFormatter(ExtractedTextFormatter.defaults())
						.withPagesPerDocument(1) // 每1页PDF作为一个Document
						.build()
		);
		// 2.读取PDF文档，拆分为Document
		List<Document> documents = reader.read();
		if (documents == null || documents.isEmpty()) {
			System.out.println("从PDF中读取的文档列表为空");
		} else {
			System.out.println("从PDF中读取的文档数量: " + documents.size());
		}
		// 3.写入向量库
		vectorStore.add(documents);
		// 4.搜索
		SearchRequest request = SearchRequest.builder()
				.query("论语中教育的目的是什么")
				.topK(1)
				.similarityThreshold(0.6)
//				.filterExpression("file_name == '中二知识笔记.pdf'")
				.build();
		List<Document> docs = vectorStore.similaritySearch(request);
		if (docs == null) {
			System.out.println("没有搜索到任何内容");
			return;
		}
		System.out.println("-------------------------------------------");
		System.out.println(docs);
		System.out.println("-------------------------------------------");
		for (Document doc : docs) {
			System.out.println(doc.getId());
			System.out.println(doc.getScore());
			System.out.println(doc.getText());
		}
	}

}
