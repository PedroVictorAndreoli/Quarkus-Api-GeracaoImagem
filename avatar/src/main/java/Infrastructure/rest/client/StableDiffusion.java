package Infrastructure.rest.client;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.List;

@RegisterRestClient(configKey="stable-diffusion-api")
public interface StableDiffusion {

    @POST
    @Path("/sdapi/v1/img2img")
    Uni<Response> img2img(Request request);

    record Request(@JsonProperty("init_images") List<String> initImages,
                   String prompt,
                   @JsonProperty("negative_prompt") String negativePrompt,
                   @JsonProperty("sampler_index") String samplerIndex,
                   @JsonProperty("sampler_name") String samplerName,
                   Integer seed,
                   @JsonProperty("denoising_strength") Double denoisingStrength,
                   @JsonProperty("cfg_scale") Double cfgScale,
                   Integer steps,
                   Integer width,
                   Integer height,
                   @JsonProperty("seed_resize_from_w") Integer seedResizeFromWidth,
                   @JsonProperty("seed_resize_from_h") Integer seedResizeFromHeight,
                   @JsonProperty("alwayson_scripts") Script alwaysonScripts) {
        public Request(String initImage) {
            this(List.of(initImage),
                    "high-quality modern Disney style image with vibrant colors and detailed character, one character",
                    "avoid ugly or disfigured elements",
                    "Euler a",
                    "Euler a",
                    12345, // Example seed for reproducibility; adjust as needed
                    0.7, // Example denoising strength; adjust based on desired clarity
                    8.0, // Example CFG scale; adjust based on desired adherence to prompt
                    30, // Increased steps for potentially higher quality
                    1024, // Increased width for more detail
                    1024, // Increased height for more detail
                    0, // No specific resize from width
                    0, // No specific resize from height
                    new Script() // Create an empty Script by default
            );
        }

        public record Script() {
            public Script {
            }

        }
    }
    record Response(List<String> images) {

    }
}

