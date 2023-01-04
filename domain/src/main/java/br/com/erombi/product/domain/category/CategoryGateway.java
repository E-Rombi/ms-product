package br.com.erombi.product.domain.category;

import br.com.erombi.product.domain.pagination.Pagination;
import br.com.erombi.product.domain.pagination.SearchQuery;

public interface CategoryGateway {

    Category create(Category aCategory);

    Pagination<Category> findAll(SearchQuery aQuery);
}
