package com.example.test.controller;

import com.example.test.dto.AutoDto;
import com.example.test.dto.DriverDto;
import com.example.test.service.ServiceAuto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.List;

/** Контроллер автомобилей */
@RestController
@RequestMapping("/auto")
@Tag(name = "автомобили")
@Slf4j
public class AutoController {
    ServiceAuto serviceAuto;

    public AutoController(ServiceAuto serviceAuto) {
        this.serviceAuto = serviceAuto;
    }

    @Operation(summary = "Получить все автомобили")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "OK",
                    content = {
                            @Content(
                                    array = @ArraySchema(schema = @Schema(implementation = DriverDto.class)))
                    }
            )
    })
    @GetMapping
    public ResponseEntity<List<AutoDto>> getAllAuto() {
        return ResponseEntity.ok(serviceAuto.getAllAuto());
    }
    @Operation(summary = "Получить автомобиль по номеру")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "OK",
                    content = {
                            @Content(
                                    schema = @Schema(implementation = DriverDto.class))
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Not Found",
                    content = {@Content(array = @ArraySchema(schema = @Schema()))}
            )
    })
    @GetMapping("/{number}")
    public ResponseEntity<AutoDto> getAuto(
            @PathVariable(name = "number") @NonNull @Parameter(description = "номер автомобиля",
                    example = "А000АА125")            String number) {
        return ResponseEntity.ok().body(serviceAuto.getAuto(number));
    }
    @Operation(summary = "Добавить новый автомобиль")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "OK",
                    content = {
                            @Content(
                                    schema = @Schema(implementation = DriverDto.class))
                    }
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Unauthorized",
                    content = {@Content(array = @ArraySchema(schema = @Schema()))}
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "Forbidden",
                    content = {@Content(array = @ArraySchema(schema = @Schema()))}
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Not Found",
                    content = {@Content(array = @ArraySchema(schema = @Schema()))}
            )
    })
    @PostMapping("/auto")
    public ResponseEntity<AutoDto> addAuto(
            @RequestBody @Valid AutoDto autoDto) {
        return ResponseEntity.ok(serviceAuto.addAuto(autoDto));
    }
    @Operation(summary = "Обновить автомобиль")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "OK"
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Unauthorized"
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "Forbidden"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Not Found"
            )
    })
    @PatchMapping("/auto")
    public ResponseEntity<?> updateAuto(
            @RequestBody AutoDto autoDto) {
        return ResponseEntity.ok().body(serviceAuto.updateAuto(autoDto));
    }
    @Operation(summary = "Удалить автомобиль по номеру")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "OK"
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Unauthorized"
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "Forbidden"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Not Found"
            )
    })
    @DeleteMapping(value = "/{number}")
    public void deleteAuto(@PathVariable(name = "number")
                               @NotBlank(message = "number не должен быть пустым")
                               @Parameter(description = "номер автомобиля",
                                       example = "А000АА125") String number) {
        serviceAuto.deleteAuto(number);
    }

}
