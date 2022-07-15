package com.sofka.reto.application.routers;

import com.sofka.reto.application.usecases.cyclismusecases.UpdateCyclistUseCase;
import com.sofka.reto.application.usecases.teamusecases.*;
import com.sofka.reto.infraestructure.rest.dto.CyclistDTO;
import com.sofka.reto.infraestructure.rest.dto.TeamDTO;
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
public class TeamRouter {
    @Bean
    @RouterOperation(beanClass = CreateTeamUseCase.class, beanMethod = "apply",
            operation = @Operation(operationId = "cyclistDTO", tags = {"Create Team"},
                    parameters = {@Parameter(in = ParameterIn.PATH, name = "id", description = "Integer"),
                            @Parameter(in = ParameterIn.PATH, name = "name", description = "String"),
                            @Parameter(in = ParameterIn.PATH, name = "code", description = "String"),
                            @Parameter(in = ParameterIn.PATH, name = "country", description = "String"),
                            @Parameter(in = ParameterIn.PATH, name = "cyclists", description = "List<Cyclist>")},

                    responses = {@ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = CyclistDTO.class))),
                            @ApiResponse(responseCode = "400", description = "Invalid Team supplied"),
                            @ApiResponse(responseCode = "404", description = "Team not found")}))
    public RouterFunction<ServerResponse> createTeam(CreateTeamUseCase createTeamUseCase) {
        Function<TeamDTO, Mono<ServerResponse>> executor = teamDTO -> createTeamUseCase.appy(teamDTO)
                .flatMap(result -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(result));

        return route(
                POST("/createTeam").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(TeamDTO.class).flatMap(executor)
        );
    }

    @Bean
    @RouterOperation(beanClass = UpdateCyclistUseCase.class, beanMethod = "apply",
            operation = @Operation(operationId = "teamDTO", tags = {"Update Team"},
                    parameters = {@Parameter(in = ParameterIn.PATH, name = "id", description = "Integer"),
                            @Parameter(in = ParameterIn.PATH, name = "name", description = "String"),
                            @Parameter(in = ParameterIn.PATH, name = "code", description = "String"),
                            @Parameter(in = ParameterIn.PATH, name = "country", description = "String"),
                            @Parameter(in = ParameterIn.PATH, name = "cyclists", description = "List<Cyclist>")},

                    responses = {@ApiResponse(responseCode = "200", description = "successful operation",
                            content = @Content(schema = @Schema(implementation = CyclistDTO.class))),
                            @ApiResponse(responseCode = "400", description = "Invalid Team supplied"),
                            @ApiResponse(responseCode = "404", description = "Team not found")}))

    public RouterFunction<ServerResponse> updateTeam(UpdateTeamUseCase updateTeamUseCase) {
        Function<TeamDTO, Mono<ServerResponse>> executor = teamDTO -> updateTeamUseCase.appy(teamDTO)
                .flatMap(result -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(result));

        return route(
                PUT("/updateTeam/{id}").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(TeamDTO.class).flatMap(executor)
        );
    }

    @Bean
    @RouterOperation(operation = @Operation(operationId = "teamDTO", tags = {"Get All Teams"},
            responses = {@ApiResponse(responseCode = "200", description = "successful operation"),
                    @ApiResponse(responseCode = "400", description = "Invalid Team supplied"),
                    @ApiResponse(responseCode = "404", description = "Teams not found")}))
    public RouterFunction<ServerResponse> getAllTeams(GetTeamUseCase getTeamUseCase) {
        return route(
                GET("/getTeams").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(
                                getTeamUseCase.get(),
                                TeamDTO.class
                        ))
        );
    }

    @Bean
    @RouterOperation(operation = @Operation(operationId = "teamDTO", tags = {"Get Teams By id"},
            parameters = {@Parameter(in = ParameterIn.PATH, name = "id", description = "Integer")},
            responses = {@ApiResponse(responseCode = "200", description = "successful operation"),
                    @ApiResponse(responseCode = "400", description = "Invalid Team ID supplied"),
                    @ApiResponse(responseCode = "404", description = "Teams not found")}))
    public RouterFunction<ServerResponse> getTeamById(GetByIdTeamUseCase getByIdTeamUseCase) {
        return route(
                GET("/getTeamById/{id}").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(
                                getByIdTeamUseCase.apply(request.pathVariable("id")),
                                TeamDTO.class
                        ))
        );

    }

    @Bean
    @RouterOperation(operation = @Operation(operationId = "teamDTO", tags = {"Get Teams By Name"},
            parameters = {@Parameter(in = ParameterIn.PATH, name = "name", description = "String")},
            responses = {@ApiResponse(responseCode = "200", description = "successful operation"),
                    @ApiResponse(responseCode = "400", description = "Invalid Team Name supplied"),
                    @ApiResponse(responseCode = "404", description = "Team not found")}))
    public RouterFunction<ServerResponse> getTeamByName(GetByNameTeamUseCase getByNameTeamUseCase) {
        return route(
                GET("/getTeamByName/{name}").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(
                                getByNameTeamUseCase.apply(request.pathVariable("name")),
                                TeamDTO.class
                        ))
        );
    }

    @Bean
    @RouterOperation(operation = @Operation(operationId = "teamDTO", tags = {"Get Cyclist By Code"},
            parameters = {@Parameter(in = ParameterIn.PATH, name = "code", description = "String")},
            responses = {@ApiResponse(responseCode = "200", description = "successful operation"),
                    @ApiResponse(responseCode = "400", description = "Invalid Team Code supplied"),
                    @ApiResponse(responseCode = "404", description = "Team not found")}))
    public RouterFunction<ServerResponse> getTeamByCode(GetByCodeTeamUseCase getByCodeTeamUseCase) {
        return route(
                GET("/getTeamByCode/{code}").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(
                                getByCodeTeamUseCase.apply(request.pathVariable("code")),
                                TeamDTO.class
                        ))
        );
    }

    @Bean
    @RouterOperation(operation = @Operation(operationId = "teamDTO", tags = {"Get Teams By Country"},
            parameters = {@Parameter(in = ParameterIn.PATH, name = "country", description = "String")},
            responses = {@ApiResponse(responseCode = "200", description = "successful operation"),
                    @ApiResponse(responseCode = "400", description = "Invalid Cyclist Team supplied"),
                    @ApiResponse(responseCode = "404", description = "Cyclist not found")}))
    public RouterFunction<ServerResponse> getTeamByCountry(GetByCountryTeamUseCase getByCountryTeamUseCase) {
        return route(
                GET("/getTeamByCountry/{country}").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(
                                getByCountryTeamUseCase.apply(request.pathVariable("country")),
                                TeamDTO.class
                        ))
        );
    }

    @Bean
    @RouterOperation(operation = @Operation(operationId = "teamDTO", tags = {"Delete Team By ID"},
            parameters = {@Parameter(in = ParameterIn.PATH, name = "id", description = "Integer")},
            responses = {@ApiResponse(responseCode = "200", description = "successful operation"),
                    @ApiResponse(responseCode = "400", description = "Invalid Team ID supplied"),
                    @ApiResponse(responseCode = "404", description = "Team not found")}))
    public RouterFunction<ServerResponse> deleteTeamById(DeleteTeamUseCase deleteTeamUseCase) {
        return route(
                DELETE("/deleteTeam/{id}").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(
                                deleteTeamUseCase.apply(request.pathVariable("id")),
                                Void.class
                        ))
        );
    }
}
