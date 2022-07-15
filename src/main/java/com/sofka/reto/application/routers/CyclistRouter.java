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
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.function.Function;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;


@Configuration
public class CyclistRouter {
    @Bean
    @RouterOperation(beanClass = CreateCyclistUseCase.class, beanMethod = "apply",
            operation = @Operation(operationId = "cyclistDTO", tags = {"Create Cyclist"},
                    parameters = {@Parameter(in = ParameterIn.PATH, name = "id", description = "Integer"),
                            @Parameter(in = ParameterIn.PATH, name = "name", description = "String"),
                            @Parameter(in = ParameterIn.PATH, name = "cyclistNumber", description = "String"),
                            @Parameter(in = ParameterIn.PATH, name = "team", description = "String"),
                            @Parameter(in = ParameterIn.PATH, name = "nationality", description = "String")},

                    responses = {@ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = CyclistDTO.class))),
                            @ApiResponse(responseCode = "400", description = "Invalid Cyclist supplied"),
                            @ApiResponse(responseCode = "404", description = "Cyclist not found")}))
    public RouterFunction<ServerResponse> createCyclist(CreateCyclistUseCase createCyclistUseCase) {
        Function<CyclistDTO, Mono<ServerResponse>> executor = cyclistDTO -> createCyclistUseCase.apply(cyclistDTO)
                .flatMap(result -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(result));

        return route(
                POST("/createCyclist").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(CyclistDTO.class).flatMap(executor)
        );
    }

    @Bean
    @RouterOperation(beanClass = UpdateCyclistUseCase.class, beanMethod = "apply",
            operation = @Operation(operationId = "cyclistDTO", tags = {"Update Cyclist"},
                    parameters = {@Parameter(in = ParameterIn.PATH, name = "id", description = "Integer"),
                            @Parameter(in = ParameterIn.PATH, name = "name", description = "String"),
                            @Parameter(in = ParameterIn.PATH, name = "cyclistNumber", description = "String"),
                            @Parameter(in = ParameterIn.PATH, name = "team", description = "String"),
                            @Parameter(in = ParameterIn.PATH, name = "nationality", description = "String")},

                    responses = {@ApiResponse(responseCode = "200", description = "successful operation",
                            content = @Content(schema = @Schema(implementation = CyclistDTO.class))),
                            @ApiResponse(responseCode = "400", description = "Invalid Cyclist supplied"),
                            @ApiResponse(responseCode = "404", description = "Cyclist not found")}))

    public RouterFunction<ServerResponse> updateCyclist(UpdateCyclistUseCase updateCyclistUseCase) {
        Function<CyclistDTO, Mono<ServerResponse>> executor = cyclistDTO -> updateCyclistUseCase.apply(cyclistDTO)
                .flatMap(result -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(result));

        return route(
                PUT("/updateCyclist/{id}").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(CyclistDTO.class).flatMap(executor)
        );
    }

    @Bean
    @RouterOperation(operation = @Operation(operationId = "cyclistDTO", tags = {"Get All Cyclists"},
            responses = {@ApiResponse(responseCode = "200", description = "successful operation"),
                    @ApiResponse(responseCode = "400", description = "Invalid Cyclist supplied"),
                    @ApiResponse(responseCode = "404", description = "Cyclist not found")}))
    public RouterFunction<ServerResponse> getAllCyclists(GetCyclistUseCase getCyclistUseCase) {
        return route(
                GET("/getCyclists").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(
                                getCyclistUseCase.get(),
                                CyclistDTO.class
                        ))
        );
    }

    @Bean
    @RouterOperation(operation = @Operation(operationId = "cyclistDTO", tags = {"Get Cyclist By id"},
            parameters = {@Parameter(in = ParameterIn.PATH, name = "id", description = "Integer")},
            responses = {@ApiResponse(responseCode = "200", description = "successful operation"),
                    @ApiResponse(responseCode = "400", description = "Invalid Cyclist ID supplied"),
                    @ApiResponse(responseCode = "404", description = "Cyclist not found")}))
    public RouterFunction<ServerResponse> getCyclistByid(GetByIdCyclistUseCase getByIdCyclistUseCase) {
        return route(
                GET("/getCyclistById/{id}").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(
                                getByIdCyclistUseCase.apply(request.pathVariable("id")),
                                CyclistDTO.class
                        ))
        );

    }

    @Bean
    @RouterOperation(operation = @Operation(operationId = "cyclistDTO", tags = {"Get Cyclist By Name"},
            parameters = {@Parameter(in = ParameterIn.PATH, name = "name", description = "String")},
            responses = {@ApiResponse(responseCode = "200", description = "successful operation"),
                    @ApiResponse(responseCode = "400", description = "Invalid Cyclist Name supplied"),
                    @ApiResponse(responseCode = "404", description = "Cyclist not found")}))
    public RouterFunction<ServerResponse> getCyclistByName(GetByCyclistNameUseCase getByCyclistNameUseCase) {
        return route(
                GET("/getCyclistByName/{name}").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(
                                getByCyclistNameUseCase.apply(request.pathVariable("name")),
                                CyclistDTO.class
                        ))
        );
    }

    @Bean
    @RouterOperation(operation = @Operation(operationId = "cyclistDTO", tags = {"Get Cyclist By Number"},
            parameters = {@Parameter(in = ParameterIn.PATH, name = "cyclistNumber", description = "String")},
            responses = {@ApiResponse(responseCode = "200", description = "successful operation"),
                    @ApiResponse(responseCode = "400", description = "Invalid Cyclist Number supplied"),
                    @ApiResponse(responseCode = "404", description = "Cyclist not found")}))
    public RouterFunction<ServerResponse> getCyclistByNumber(GetByCyclistNumberUseCase getByCyclistNumberUseCase) {
        return route(
                GET("/getCyclistByNumber/{cyclistNumber}").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(
                                getByCyclistNumberUseCase.apply(request.pathVariable("cyclistNumber")),
                                CyclistDTO.class
                        ))
        );
    }

    @Bean
    @RouterOperation(operation = @Operation(operationId = "cyclistDTO", tags = {"Get Cyclist By Team"},
            parameters = {@Parameter(in = ParameterIn.PATH, name = "team", description = "String")},
            responses = {@ApiResponse(responseCode = "200", description = "successful operation"),
                    @ApiResponse(responseCode = "400", description = "Invalid Cyclist Team supplied"),
                    @ApiResponse(responseCode = "404", description = "Cyclist not found")}))
    public RouterFunction<ServerResponse> getCyclistByTeam(GetByCyclistTeamUseCase getByCyclistTeamUseCase) {
        return route(
                GET("/getCyclistByTeam/{team}").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(
                                getByCyclistTeamUseCase.apply(request.pathVariable("team")),
                                CyclistDTO.class
                        ))
        );
    }

    @Bean
    @RouterOperation(operation = @Operation(operationId = "cyclistDTO", tags = {"Get Cyclist By Nationality"},
            parameters = {@Parameter(in = ParameterIn.PATH, name = "nationality;", description = "String")},
            responses = {@ApiResponse(responseCode = "200", description = "successful operation"),
                    @ApiResponse(responseCode = "400", description = "Invalid Cyclist Nationality supplied"),
                    @ApiResponse(responseCode = "404", description = "Cyclist not found")}))
    public RouterFunction<ServerResponse> getCyclistByNationality(GetByCyclistNationalityUseCase getByCyclistNationalityUseCase) {
        return route(
                GET("/getCyclistByNationality/{nationality}").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(
                                getByCyclistNationalityUseCase.apply(request.pathVariable("nationality")),
                                CyclistDTO.class
                        ))
        );
    }

    @Bean
    @RouterOperation(operation = @Operation(operationId = "cyclistDTO", tags = {"Delete Cyclist By ID"},
            parameters = {@Parameter(in = ParameterIn.PATH, name = "id", description = "Integer")},
            responses = {@ApiResponse(responseCode = "200", description = "successful operation"),
                    @ApiResponse(responseCode = "400", description = "Invalid Cyclist ID supplied"),
                    @ApiResponse(responseCode = "404", description = "Cyclist not found")}))
    public RouterFunction<ServerResponse> deleteCyclistById(DeleteCyclistUseCase deleteCyclistUseCase) {
        return route(
                DELETE("/deleteCyclist/{id}").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(
                                deleteCyclistUseCase.apply(request.pathVariable("id")),
                                Void.class
                        ))
        );
    }
}
