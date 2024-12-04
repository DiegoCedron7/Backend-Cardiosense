package com.cardiosense.cardiosense.model.MercadoPago;

import lombok.Builder;
import lombok.Data;

import java.sql.Date;

@Data
@Builder
public class MercadoPagoPayment {
    private String action;
    private String api_version;
    private DataMP data;
    private Date date_created;
    private long id;
    private boolean live_mode;
    private String type;
    private String user_id;
}
