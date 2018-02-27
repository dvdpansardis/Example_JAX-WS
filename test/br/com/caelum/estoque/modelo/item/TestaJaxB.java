package br.com.caelum.estoque.modelo.item;

import org.junit.Before;
import org.junit.Test;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLStreamException;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;


public class TestaJaxB {

	private Item item;
	private String xml;

	@Before
	public void setup() throws IOException {
		item = new Item.Builder().comCodigo("MEA").comNome("MEAN").comQuantidade(4).comTipo("Livro").build();
		byte[] encoded = Files.readAllBytes(Paths.get("test/item.xml"));
		xml = new String(encoded, "UTF-8");
	}

	@Test
	public void marshallerUmItem() throws JAXBException, XMLStreamException {
		JAXBContext context = JAXBContext.newInstance(Item.class);
		Marshaller marshaller = context.createMarshaller();
		StringWriter writer = new StringWriter();
		marshaller.marshal(item, writer);

		assertEquals(xml.trim(), writer.toString().trim());
	}

	@Test
	public void unmarshallerUmItemEmXML() throws JAXBException {
		JAXBContext context = JAXBContext.newInstance(Item.class);
		Unmarshaller unmarshaller = context.createUnmarshaller();
		Item itemUnMarshalled = (Item) unmarshaller.unmarshal(new File("test/item.xml"));

		assertEquals(item, itemUnMarshalled);
	}

}
