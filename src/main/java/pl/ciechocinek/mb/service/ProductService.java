package pl.ciechocinek.mb.service;

import java.util.Map;

import pl.ciechocinek.mb.domain.Product;
import pl.ciechocinek.mb.shared.AbstractCRUD;

public interface ProductService extends AbstractCRUD<Product> {
	public Map<Integer, Product> readAllMap();
}
