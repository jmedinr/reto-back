package com.sofka.reto.application.routers;

import com.sofka.reto.application.usecases.cyclismusecases.*;
import com.sofka.reto.application.usecases.teamusecases.*;
import com.sofka.reto.infraestructure.rest.dto.TeamDTO;
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
public class TeamRouter {
    @Bean
    @RouterOperations(
            {
                    @RouterOperation(path = "/getTeams", produces = {MediaType.APPLICATION_JSON_VALUE},
                            method = RequestMethod.GET,
                            beanClass = GetTeamUseCase.class,
                            beanMethod = "get",
                            operation = @Operation(operationId = "getTeams",
                                    responses = {
                                            @ApiResponse(responseCode = "200", description = "OK",
                                                    content = @Content(schema = @Schema(implementation = TeamDTO.class))),
                                            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
                                            @ApiResponse(responseCode = "400", description = "Bad Request"),
                                            @ApiResponse(responseCode = "404", description = "Not Found")},
                                    parameters = {
                                            @Parameter(in = ParameterIn.PATH, name = "team", description = "Team")}
                            )),
                    @RouterOperation(path = "/getTeamsById/{teamId}",
                            produces = {MediaType.APPLICATION_JSON_VALUE},
                            method = RequestMethod.GET,
                            beanClass = GetByIdTeamUseCase.class,
                            beanMethod = "apply",
                            operation = @Operation(operationId = "getTeamById",
                                    responses = {
                                            @ApiResponse(responseCode = "200", description = "OK",
                                                    content = @Content(schema = @Schema(implementation = TeamDTO.class))),
                                            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
                                            @ApiResponse(responseCode = "400", description = "Bad Request"),
                                            @ApiResponse(responseCode = "404", description = "Not Found")},
                                    parameters = {
                                            @Parameter(in = ParameterIn.PATH, name = "teamId", description = "Team Id")})
                    ),
                    @RouterOperation(path = "/getTeamsByName/{teamName}",
                            produces = {MediaType.APPLICATION_JSON_VALUE},
                            method = RequestMethod.GET,
                            beanClass = GetByNameTeamUseCase.class,
                            beanMethod = "apply",
                            operation = @Operation(operationId = "getCyclistByName",
                                    responses = {
                                            @ApiResponse(responseCode = "200", description = "OK",
                                                    content = @Content(schema = @Schema(implementation = TeamDTO.class))),
                                            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
                                            @ApiResponse(responseCode = "400", description = "Bad Request"),
                                            @ApiResponse(responseCode = "404", description = "Not Found")},
                                    parameters = {
                                            @Parameter(in = ParameterIn.PATH, name = "teamName", description = "Team Name")})
                    ),
                    @RouterOperation(path = "/getTeamByCode/{codeTeam}",
                            produces = {MediaType.APPLICATION_JSON_VALUE},
                            method = RequestMethod.GET,
                            beanClass = GetByCodeTeamUseCase.class,
                            beanMethod = "apply",
                            operation = @Operation(operationId = "getCyclistByCode",
                                    responses = {
                                            @ApiResponse(responseCode = "200", description = "OK",
                                                    content = @Content(schema = @Schema(implementation = TeamDTO.class))),
                                            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
                                            @ApiResponse(responseCode = "400", description = "Bad Request"),
                                            @ApiResponse(responseCode = "404", description = "Not Found")},
                                    parameters = {
                                            @Parameter(in = ParameterIn.PATH, name = "codeTeam", description = "Code Team")})
                    ),
                    @RouterOperation(path = "/getTeamByCountry/{teamCountry}",
                            produces = {MediaType.APPLICATION_JSON_VALUE},
                            method = RequestMethod.GET,
                            beanClass = GetByCountryTeamUseCase.class,
                            beanMethod = "apply",
                            operation = @Operation(operationId = "getCyclistByCountry",
                                    responses = {
                                            @ApiResponse(responseCode = "200", description = "OK",
                                                    content = @Content(schema = @Schema(implementation = TeamDTO.class))),
                                            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
                                            @ApiResponse(responseCode = "400", description = "Bad Request"),
                                            @ApiResponse(responseCode = "404", description = "Not Found")},
                                    parameters = {
                                            @Parameter(in = ParameterIn.PATH, name = "teamCountry", description = "Team Country")})
                    ),
                    @RouterOperation(path = "/createTeam",
                            produces = {MediaType.APPLICATION_JSON_VALUE},
                            method = RequestMethod.POST,
                            beanClass = CreateTeamUseCase.class,
                            beanMethod = "apply",
                            operation = @Operation(operationId = "createTeam",
                                    responses = {
                                            @ApiResponse(responseCode = "200", description = "OK",
                                                    content = @Content(schema = @Schema(implementation = TeamDTO.class))),
                                            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
                                            @ApiResponse(responseCode = "400", description = "Bad Request"),
                                            @ApiResponse(responseCode = "404", description = "Not Found")})
                    ),
                    @RouterOperation(path = "/updateTeam",
                            produces = {MediaType.APPLICATION_JSON_VALUE},
                            method = RequestMethod.PUT,
                            beanClass = UpdateTeamUseCase.class,
                            beanMethod = "apply",
                            operation = @Operation(operationId = "updateTeam",
                                    responses = {
                                            @ApiResponse(responseCode = "200", description = "OK",
                                                    content = @Content(schema = @Schema(implementation = TeamDTO.class))),
                                            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
                                            @ApiResponse(responseCode = "400", description = "Bad Request"),
                                            @ApiResponse(responseCode = "404", description = "Not Found")})
                    ),
                    @RouterOperation(path = "/deleteTeam/{teamId}",
                            produces = {MediaType.APPLICATION_JSON_VALUE},
                            method = RequestMethod.DELETE,
                            beanClass = DeleteTeamUseCase.class,
                            beanMethod = "apply",
                            operation = @Operation(operationId = "deleteTeam",
                                    responses = {
                                            @ApiResponse(responseCode = "200", description = "OK",
                                                    content = @Content(schema = @Schema(implementation = TeamDTO.class))),
                                            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
                                            @ApiResponse(responseCode = "400", description = "Bad Request"),
                                            @ApiResponse(responseCode = "404", description = "Not Found")})
                    )
            }

    )
    RouterFunction<ServerResponse> routerFunctionCyclist(GetTeamUseCase getTeamUseCase, GetByNameTeamUseCase getByNameTeamUseCase,
                                                         GetByCodeTeamUseCase getByCodeTeamUseCase, GetByCountryTeamUseCase getByCountryTeamUseCase,
                                                         GetByIdTeamUseCase getByIdTeamUseCase, UpdateTeamUseCase updateTeamUseCase,
                                                         DeleteTeamUseCase deleteTeamUseCase, CreateTeamUseCase createTeamUseCase) {
        return RouterFunctions
                .route(RequestPredicates.GET("/getTeams"), request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(getTeamUseCase.get(), TeamDTO.class))
                )
                .andRoute(RequestPredicates.GET("/getTeamsById/{teamId}"), request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(getByIdTeamUseCase.apply(request.pathVariable("teamId")), TeamDTO.class))
                )
                .andRoute(RequestPredicates.GET("/getTeamsByName/{teamName}"), request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(getByNameTeamUseCase.apply(request.pathVariable("teamName")), TeamDTO.class))
                )
                .andRoute(RequestPredicates.GET("/getTeamByCode/{codeTeam}"), request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(getByCodeTeamUseCase.apply(request.pathVariable("codeTeam")), TeamDTO.class))
                )
                .andRoute(RequestPredicates.GET("/getTeamByCountry/{teamCountry}"), request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(getByCountryTeamUseCase.apply(request.pathVariable("teamCountry")), TeamDTO.class))
                )
                .andRoute(RequestPredicates.POST("/createTeam").and(accept(MediaType.APPLICATION_JSON)),
                        request -> request.bodyToMono(TeamDTO.class).flatMap(
                                teamDTO -> createTeamUseCase.appy(teamDTO)
                                        .flatMap(result -> ServerResponse.ok()
                                                .contentType(MediaType.TEXT_PLAIN)
                                                .bodyValue(result))
                        )
                )
                .andRoute(RequestPredicates.DELETE("/deleteTeam/{teamId}").and(accept(MediaType.APPLICATION_JSON)),  request -> ServerResponse.accepted()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(deleteTeamUseCase.apply(request.pathVariable("teamId")), Void.class))
                )
                .andRoute(RequestPredicates.PUT("/updateTeam").and(accept(MediaType.APPLICATION_JSON)), request -> request.bodyToMono(TeamDTO.class)
                        .flatMap(teamDTO -> updateTeamUseCase.appy(teamDTO)
                                .flatMap(result -> ServerResponse.ok()
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .bodyValue(result))
                        )
                );

    }
}
