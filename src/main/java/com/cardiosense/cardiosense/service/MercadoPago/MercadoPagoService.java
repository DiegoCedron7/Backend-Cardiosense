package com.cardiosense.cardiosense.service.MercadoPago;

import com.cardiosense.cardiosense.model.User.UserEntity;
import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.preference.*;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.preference.Preference;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class MercadoPagoService {

    public MercadoPagoService() {
        String ACCESS_TOKEN = "TEST-6601521810006519-120501-9a9343e8bee92e590a242249e2b7f611-485417535";
        MercadoPagoConfig.setAccessToken(ACCESS_TOKEN);
    }


    public Preference createPreference(Optional<UserEntity> user) throws MPApiException, MPException {
        if (user.isEmpty()) {
            throw new MPException("User cannot be null");
        }

        List<PreferenceItemRequest> items = new ArrayList<>();
        items.add(PreferenceItemRequest.builder()
                .id(user.get().getId())
                .title("Cardiosense Plan")
                .description("Cardiosense plan for 6 months")
                .unitPrice(BigDecimal.valueOf(300))
                .quantity(1)
                .build());


        PreferenceBackUrlsRequest backUrls = createBackUrls();
        PreferenceRequest preferenceRequest = PreferenceRequest.builder()
                .payer(PreferencePayerRequest.builder()
                        .name(user.get().getName())
                        .email(user.get().getEmail())
                        .build())
                .backUrls(backUrls)
                .items(items)
                .additionalInfo("Cardiosense plan for 6 months")
                .externalReference(user.get().getId())
                .marketplace("Cardiosense")
                //.notificationUrl("https://webhook.site/be43cfe2-c8e2-4001-9bba-e624b3a494c9")
                .build();

        PreferenceClient client = new PreferenceClient();
        return client.create(preferenceRequest);
    }

    public PreferenceBackUrlsRequest createBackUrls() {
        return PreferenceBackUrlsRequest.builder()
                .success("https://cardiosense.vercel.app/purchase-completed")
                .failure("https://cardiosense.vercel.app")
                .pending("https://cardiosense.vercel.app")
                .build();
    }
}
