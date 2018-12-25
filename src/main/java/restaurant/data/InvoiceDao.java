package restaurant.data;

import restaurant.business.cart.Invoice;

import java.util.List;

public interface InvoiceDao {
    boolean insert(Invoice invoice);
    List<Invoice> selectUnprocessedInvoices();
}
