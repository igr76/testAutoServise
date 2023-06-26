package com.example.test.controller;

import com.example.test.dto.DriverDto;
import com.example.test.service.ServiceDriver;
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
import javax.validation.constraints.NotBlank;
import java.util.List;

/** Контроллер водителей */
@RestController
@RequestMapping("/driver")
@Tag(name = "Водители")
@Slf4j
public class DriverController {
    private final ServiceDriver serviceDriver;

    public DriverController(ServiceDriver serviceDriver) {
        this.serviceDriver = serviceDriver;
    }

    @Operation(summary = "Получить всех водителей")
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
    public ResponseEntity<List<DriverDto>> getAllDriver() {
        return ResponseEntity.ok(serviceDriver.getAllDriver());
    }
    @Operation(summary = "Получить водителя по пасспорту")
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
    @GetMapping("/{passport}")
    public ResponseEntity<DriverDto> getDriver(
            @PathVariable(name = "passport") @NonNull @Parameter(description = "Больше 0, Например 1") Integer passport) {
        return ResponseEntity.ok().body(serviceDriver.getDriver(passport));
    }
    @Operation(summary = "Добавить нового водителя")
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
    @PostMapping()
    public ResponseEntity<DriverDto> addDriver(
            @RequestBody @Valid DriverDto driverDto) {
        return ResponseEntity.ok(serviceDriver.addDriver(driverDto));
    }
    @Operation(summary = "Обновить водителя")
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
    @PatchMapping()
    public ResponseEntity<?> updateDriver(
            @RequestBody DriverDto driverDto) {
        return ResponseEntity.ok().body(serviceDriver.updateDriver(driverDto));
    }
    @Operation(summary = "Удалить водителя по номеру паспорта")
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
    @DeleteMapping(value = "/{passport}")
    public void deleteDriver(@PathVariable(name = "passport")
                               @NotBlank(message = "номер паспорта не должен быть пустым")
                               @Parameter(description = "паспорт владельца",
                                       example = "0100345678") Integer passport) {
        serviceDriver.deleteDriver(passport);
    }
    @Operation(summary = "зачислить валюту")
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
    @PatchMapping("/{money}/dollar+")
    public ResponseEntity<?> plusDollar(@PathVariable(name = "money")
                                            @NotBlank(message = "сумма не должна быть пустой")
                                            @Parameter(description = "сумма операции",
                                                    example = "100") double money,
            @RequestBody DriverDto driverDto) {
        return ResponseEntity.ok().body(serviceDriver.plusDollar(money,driverDto));
    }
    @Operation(summary = "изъять валюту")
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
    @PatchMapping("/{money}/dollar-")
    public ResponseEntity<?> minusDollar(@PathVariable(name = "money")
                                             @NotBlank(message = "сумма не должна быть пустой")
                                             @Parameter(description = "сумма операции",
                                                     example = "100") double money,
                                        @RequestBody DriverDto driverDto) {
        return ResponseEntity.ok().body(serviceDriver.minusDollar(money,driverDto));
    }
}
