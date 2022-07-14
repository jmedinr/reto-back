package com.sofka.reto.application.routers;

import com.sofka.reto.application.usecases.cyclismusecases.*;
import com.sofka.reto.infraestructure.rest.dto.CyclistDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springdoc.core.annotations.RouterOperation;
import org.springdoc.core.annotations.RouterOperations;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;


import static org.springframework.web.reactive.function.server.RequestPredicates.accept;


@RestController
@Configuration
public class CyclistRouter {
    @Bean
    @RouterOperations(
            {
                    @RouterOperation(path = "/getCyclists", produces = {MediaType.APPLICATION_JSON_VALUE},
                            method = RequestMethod.GET,
                            beanClass = GetCyclistUseCase.class,
                            beanMethod = "get",
                            operation = @Operation(operationId = "getCyclists",
                                    responses = {
                                            @ApiResponse(responseCode = "200", description = "OK",
                                                    content = @Content(schema = @Schema(implementation = CyclistDTO.class))),
                                            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
                                            @ApiResponse(responseCode = "400", description = "Bad Request"),
                                            @ApiResponse(responseCode = "404", description = "Not Found")},
                                    parameters = {
                                            @Parameter(in = ParameterIn.PATH, name = "cyclist", description = "Cyclist")}
                            )),
                    @RouterOperation(path = "/getCyclistsById/{cyclistId}",
                            produces = {MediaType.APPLICATION_JSON_VALUE},
                            method = RequestMethod.GET,
                            beanClass = GetByIdCyclistUseCase.class,
                            beanMethod = "apply",
                            operation = @Operation(operationId = "getCyclistById",
                                    responses = {
                                            @ApiResponse(responseCode = "200", description = "OK",
                                                    content = @Content(schema = @Schema(implementation = CyclistDTO.class))),
                                            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
                                            @ApiResponse(responseCode = "400", description = "Bad Request"),
                                            @ApiResponse(responseCode = "404", description = "Not Found")},
                                    parameters = {
                                            @Parameter(in = ParameterIn.PATH, name = "cyclistId", description = "Cyclist Id")})
                    ),
                    @RouterOperation(path = "/getCyclistsByNumber/{cyclistNumber}",
                            produces = {MediaType.APPLICATION_JSON_VALUE},
                            method = RequestMethod.GET,
                            beanClass = GetByCyclistNumberUseCase.class,
                            beanMethod = "apply",
                            operation = @Operation(operationId = "getCyclistByNumber",
                                    responses = {
                                            @ApiResponse(responseCode = "200", description = "OK",
                                                    content = @Content(schema = @Schema(implementation = CyclistDTO.class))),
                                            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
                                            @ApiResponse(responseCode = "400", description = "Bad Request"),
                                            @ApiResponse(responseCode = "404", description = "Not Found")},
                                    parameters = {
                                            @Parameter(in = ParameterIn.PATH, name = "cyclistNumber", description = "Cyclist Number")})
                    ),
                    @RouterOperation(path = "/getCyclistsByTeam/{cyclistTeam}",
                            produces = {MediaType.APPLICATION_JSON_VALUE},
                            method = RequestMethod.GET,
                            beanClass = GetByCyclistTeamUseCase.class,
                            beanMethod = "apply",
                            operation = @Operation(operationId = "getCyclistByTeam",
                                    responses = {
                                            @ApiResponse(responseCode = "200", description = "OK",
                                                    content = @Content(schema = @Schema(implementation = CyclistDTO.class))),
                                            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
                                            @ApiResponse(responseCode = "400", description = "Bad Request"),
                                            @ApiResponse(responseCode = "404", description = "Not Found")},
                                    parameters = {
                                            @Parameter(in = ParameterIn.PATH, name = "cyclistTeam", description = "Cyclist Team")})
                    ),
                    @RouterOperation(path = "/getCyclistsByNationality/{cyclistNationality}",
                            produces = {MediaType.APPLICATION_JSON_VALUE},
                            method = RequestMethod.GET,
                            beanClass = GetByCyclistTeamUseCase.class,
                            beanMethod = "apply",
                            operation = @Operation(operationId = "getCyclistByTeam",
                                    responses = {
                                            @ApiResponse(responseCode = "200", description = "OK",
                                                    content = @Content(schema = @Schema(implementation = CyclistDTO.class))),
                                            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
                                            @ApiResponse(responseCode = "400", description = "Bad Request"),
                                            @ApiResponse(responseCode = "404", description = "Not Found")},
                                    parameters = {
                                            @Parameter(in = ParameterIn.PATH, name = "cyclistNationality", description = "Cyclist Nationality")})
                    ),
                    @RouterOperation(path = "/createCyclist",
                            produces = {MediaType.APPLICATION_JSON_VALUE},
                            method = RequestMethod.POST,
                            beanClass = CreateCyclistUseCase.class,
                            beanMethod = "apply",
                            operation = @Operation(operationId = "createCyclist",
                                    responses = {
                                            @ApiResponse(responseCode = "200", description = "OK",
                                                    content = @Content(schema = @Schema(implementation = CyclistDTO.class))),
                                            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
                                            @ApiResponse(responseCode = "400", description = "Bad Request"),
                                            @ApiResponse(responseCode = "404", description = "Not Found")})
                    ),
                    @RouterOperation(path = "/updateCyclist",
                            produces = {MediaType.APPLICATION_JSON_VALUE},
                            method = RequestMethod.PUT,
                            beanClass = UpdateCyclistUseCase.class,
                            beanMethod = "apply",
                            operation = @Operation(operationId = "updateCyclist",
                                    responses = {
                                            @ApiResponse(responseCode = "200", description = "OK",
                                                    content = @Content(schema = @Schema(implementation = CyclistDTO.class))),
                                            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
                                            @ApiResponse(responseCode = "400", description = "Bad Request"),
                                            @ApiResponse(responseCode = "404", description = "Not Found")})
                    ),
                    @RouterOperation(path = "/deleteCyclist/{cyclistId}",
                            produces = {MediaType.APPLICATION_JSON_VALUE},
                            method = RequestMethod.DELETE,
                            beanClass = DeleteCyclistUseCase.class,
                            beanMethod = "apply",
                            operation = @Operation(operationId = "deleteCyclist",
                                    responses = {
                                            @ApiResponse(responseCode = "200", description = "OK",
                                                    content = @Content(schema = @Schema(implementation = CyclistDTO.class))),
                                            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
                                            @ApiResponse(responseCode = "400", description = "Bad Request"),
                                            @ApiResponse(responseCode = "404", description = "Not Found")})
                    )
            }

    )

    RouterFunction<ServerResponse> routerFunctionCyclist(GetCyclistUseCase getCyclistUseCase, GetByIdCyclistUseCase getByIdCyclistUseCase,
                                                         GetByCyclistTeamUseCase getByCyclistTeamUseCase, GetByCyclistNumberUseCase getByCyclistNumberUseCase,
                                                         GetByCyclistNationalityUseCase getByCyclistNationalityUseCase, DeleteCyclistUseCase deleteCyclistUseCase,
                                                         CreateCyclistUseCase createCyclistUseCase, UpdateCyclistUseCase updateCyclistUseCase) {
        return RouterFunctions
                .route(RequestPredicates.GET("/getCyclists"), request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(getCyclistUseCase.get(), CyclistDTO.class))
                )
                .andRoute(RequestPredicates.GET("/getCyclistsById/{cyclistId}"), request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(getByIdCyclistUseCase.apply(request.pathVariable("cyclistId")), CyclistDTO.class))
                )
                .andRoute(RequestPredicates.GET("/getCyclistsByNumber/{cyclistNumber}"), request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(getByCyclistNumberUseCase.apply(request.pathVariable("cyclistNumber")), CyclistDTO.class))
                )
                .andRoute(RequestPredicates.GET("/getCyclistsByTeam/{cyclistTeam}"), request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(getByCyclistTeamUseCase.apply(request.pathVariable("cyclistTeam")), CyclistDTO.class))
                )
                .andRoute(RequestPredicates.GET("/getCyclistsByNationality/{cyclistNationality}"), request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(getByCyclistNationalityUseCase.apply(request.pathVariable("cyclistNationality")), CyclistDTO.class))
                )
                .andRoute(RequestPredicates.POST("/createCyclist").and(accept(MediaType.APPLICATION_JSON)),
                        request -> request.bodyToMono(CyclistDTO.class).flatMap(
                                cyclistDTO -> createCyclistUseCase.apply(cyclistDTO)
                                        .flatMap(result -> ServerResponse.ok()
                                                .contentType(MediaType.TEXT_PLAIN)
                                                .bodyValue(result))
                        )
                )
                .andRoute(RequestPredicates.DELETE("/deleteCyclist/{cyclistId}").and(accept(MediaType.APPLICATION_JSON)),  request -> ServerResponse.accepted()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(deleteCyclistUseCase.apply(request.pathVariable("cyclistId")), Void.class))
                )
                .andRoute(RequestPredicates.PUT("/updateCyclist").and(accept(MediaType.APPLICATION_JSON)), request -> request.bodyToMono(CyclistDTO.class)
                        .flatMap(cyclistDTO -> updateCyclistUseCase.apply(cyclistDTO)
                                .flatMap(result -> ServerResponse.ok()
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .bodyValue(result))
                        )
                );

    }
}
