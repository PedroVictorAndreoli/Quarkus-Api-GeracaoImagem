package Infrastructure.rest.client;

import domain.models.ProfilePhoto;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import org.apache.commons.io.FileUtils;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;
import com.fasterxml.jackson.databind.ObjectMapper; // Import para converter em JSON


import java.io.File;
import java.io.IOException;
import java.util.Base64;

@ApplicationScoped
public class StableDiffusionService {
    private final StableDiffusion stableDiffusion;

    public StableDiffusionService(@RestClient StableDiffusion stableDiffusion) {
        this.stableDiffusion = stableDiffusion;
    }


    public Uni<String> generate(ProfilePhoto profilePhoto) throws IOException {
        var fileContent = FileUtils.readFileToByteArray(new File(profilePhoto.originalPhoto()));
        var encodedString = Base64.getEncoder().encodeToString(fileContent);

        return stableDiffusion.img2img(new StableDiffusion.Request(encodedString))
                .onItem()
                .transform(response -> response.images().stream().findFirst().orElseThrow());
    }


}
