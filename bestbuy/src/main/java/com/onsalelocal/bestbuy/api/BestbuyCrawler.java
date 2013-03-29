package com.onsalelocal.bestbuy.api;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import com.gaoshin.onsalelocal.api.Store;

public class BestbuyCrawler {

	/**
	 * Read nodes(stores) from xml file, and save them in List
	 * 
	 * @param fileName
	 *            AbsolutePath
	 * @return List<store>
	 * @throws DocumentException
	 */
	public List<Store> listStores(String fileName) {

		List<Store> result = new ArrayList<Store>();
		SAXReader reader = new SAXReader();
		Document document = null;
		try {
			document = reader.read(fileName);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String xpath = "//stores/store";

		@SuppressWarnings("unchecked")
		List<Node> nodes = document.selectNodes(xpath);

		// System.out.println("111" + nodes.size());
		getStorefromNode(result, nodes);
		// System.out.println("" + result.size());
		return result;
	}

	/**
	 * Parse node from xml file, and save in store instance
	 * 
	 * @param List
	 *            <Store>: the ListDir
	 * @param List
	 *            <Node>: nodes from xml file
	 */
	public void getStorefromNode(List<Store> list, List<Node> n) {

		for (Node node : n) {

			Store store = new Store();

			Node sotreId = node.selectSingleNode("storeId");
			Node name = node.selectSingleNode("name");
			// Node longName = node.selectSingleNode("longName");
			Node address = node.selectSingleNode("address");
			Node city = node.selectSingleNode("city");
			Node region = node.selectSingleNode("region");
			Node postalCode = node.selectSingleNode("postalCode");
			// Node fullPostalCode = node.selectSingleNode("fullPostalCode");
			Node country = node.selectSingleNode("country");
			Node lat = node.selectSingleNode("lat");
			Node lng = node.selectSingleNode("lng");
			Node phone = node.selectSingleNode("phone");
			// Node hours = node.selectSingleNode("hours");

			store.setId(sotreId.getText());
			store.setName(name.getText());
			store.setAddress(address.getText());
			store.setCity(city.getText());
			store.setState(region.getText());
			store.setZipcode(postalCode.getText());
			store.setCountry(country.getText());
			store.setLatitude(new BigDecimal(lat.getText()));
			store.setLongitude(new BigDecimal(lng.getText()));
			store.setPhone(phone.getText());

			list.add(store);

		}
	}

}
