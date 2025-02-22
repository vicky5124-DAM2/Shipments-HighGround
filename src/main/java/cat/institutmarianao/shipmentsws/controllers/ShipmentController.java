package cat.institutmarianao.shipmentsws.controllers;

import cat.institutmarianao.shipmentsws.ShipmentswsApplication;
import cat.institutmarianao.shipmentsws.model.Action;
import cat.institutmarianao.shipmentsws.model.Shipment;
import cat.institutmarianao.shipmentsws.model.Shipment.Category;
import cat.institutmarianao.shipmentsws.model.Shipment.Status;
import cat.institutmarianao.shipmentsws.services.ActionService;
import cat.institutmarianao.shipmentsws.services.ShipmentService;
import cat.institutmarianao.shipmentsws.validation.groups.OnActionCreate;
import cat.institutmarianao.shipmentsws.validation.groups.OnShipmentCreate;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/* Swagger */
@Tag(name = "Shipment", description = "ShipmentController API")
/**/
@RestController
@RequestMapping("/shipments")
@Validated
public class ShipmentController {

    //	@Autowired
//	private UserService userService;
//
    @Autowired
    private ShipmentService shipmentService;

    @Autowired
    private ActionService actionService;


    @Operation(summary = "Find all shipments")
    @ApiResponse(responseCode = "200", content = {
            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Shipment.class)))}, description = "Shipments retrieved ok")
    @GetMapping("/find/all")
    public List<Shipment> findAll(@RequestParam(value = "status", required = false) Status status,
                                  @RequestParam(value = "receivedBy", required = false) String receivedBy,
                                  @RequestParam(value = "courierAssigned", required = false) String courierAssigned,
                                  @RequestParam(value = "category", required = false) Category category,
                                  @RequestParam(value = "from", required = false) @DateTimeFormat(pattern = ShipmentswsApplication.DATE_PATTERN) @Parameter(description = ShipmentswsApplication.DATE_PATTERN) Date from,
                                  @RequestParam(value = "to", required = false) @DateTimeFormat(pattern = ShipmentswsApplication.DATE_PATTERN) @Parameter(description = ShipmentswsApplication.DATE_PATTERN) Date to) {

        return shipmentService.findAll(status, receivedBy, courierAssigned, category, from, to);
    }

    /* Swagger */
    @Operation(summary = "Find all PENDING shipments")
    @ApiResponse(responseCode = "200", content = {
            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Shipment.class)))}, description = "Shipments retrieved ok")
    /**/
    @GetMapping("/find/all/PENDING")
    public List<Shipment> findAllPending(@RequestParam(value = "receivedBy", required = false) String receivedBy,
                                         @RequestParam(value = "courierAssigned", required = false) String courierAssigned,
                                         @RequestParam(value = "category", required = false) Category category,
                                         @RequestParam(value = "from", required = false) @DateTimeFormat(pattern = ShipmentswsApplication.DATE_PATTERN) @Parameter(description = ShipmentswsApplication.DATE_PATTERN) Date from,
                                         @RequestParam(value = "to", required = false) @DateTimeFormat(pattern = ShipmentswsApplication.DATE_PATTERN) @Parameter(description = ShipmentswsApplication.DATE_PATTERN) Date to) {

        return shipmentService.findAll(Status.PENDING, receivedBy, courierAssigned, category, from, to);
    }

    /* Swagger */
    @Operation(summary = "Find all IN_PROCESS shipments")
    @ApiResponse(responseCode = "200", content = {
            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Shipment.class)))}, description = "Shipments retrieved ok")
    /**/
    @GetMapping("/find/all/IN_PROCESS")
    public List<Shipment> findAllInProcess(@RequestParam(value = "receivedBy", required = false) String receivedBy,
                                           @RequestParam(value = "courierAssigned", required = false) String courierAssigned,
                                           @RequestParam(value = "category", required = false) Category category,
                                           @RequestParam(value = "from", required = false) @DateTimeFormat(pattern = ShipmentswsApplication.DATE_PATTERN) @Parameter(description = ShipmentswsApplication.DATE_PATTERN) Date from,
                                           @RequestParam(value = "to", required = false) @DateTimeFormat(pattern = ShipmentswsApplication.DATE_PATTERN) @Parameter(description = ShipmentswsApplication.DATE_PATTERN) Date to) {

        return shipmentService.findAll(Status.IN_PROCESS, receivedBy, courierAssigned, category, from, to);
    }

    /* Swagger */
    @Operation(summary = "Get shipment by id")
    @ApiResponse(responseCode = "200", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Shipment.class))}, description = "Shipment retrieved ok")
    @ApiResponse(responseCode = "404", content = {
            @Content(mediaType = "application/json")}, description = "Shipment not found")
    /**/
    @GetMapping("/get/by/id/{shipmentId}")
    public Shipment findById(@PathVariable("shipmentId") @Positive Long shipmentId) {
        return shipmentService.getById(shipmentId);
    }

    /* Swagger */
    @Operation(summary = "Get shipment tracking by shipment id")
    @ApiResponse(responseCode = "200", content = {
            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Action.class)))}, description = "Tracking retrieved ok")
    /**/
    @GetMapping("/find/tracking/by/id/{shipmentId}")
    public List<Action> findTrackingByTicketId(@PathVariable("shipmentId") @Positive Long shipmentId) {
        return shipmentService.getTrackingById(shipmentId);
    }

    /* Swagger */
    @Operation(summary = "Create new shipment")
    @ApiResponse(responseCode = "200", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Shipment.class))}, description = "Shipment saved ok")
    /**/
    @PostMapping("/save/shipment")
    @Validated(OnShipmentCreate.class)
    //public Shipment reception(@Parameter(schema = @Schema(implementation = Shipment.class)) @RequestBody @Valid Shipment shipment) {
    public Shipment reception(@RequestBody @Valid Shipment shipment) {
        return shipmentService.save(shipment);
    }


    /* Swagger */
    @Operation(summary = "Save an action of a shipment (in its tracking). Action at the end of shipment tracking")
    /**/
    @ApiResponse(responseCode = "200", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Action.class))}, description = "Action saved ok")
    @PostMapping("/save/action")
    @Validated(OnActionCreate.class)
    public Action saveAction(@RequestBody @Valid Action action) {
        return actionService.save(action);
    }

    /* Swagger */
    @Operation(summary = "Delete a shipment")
    @ApiResponse(responseCode = "200", content = {
            @Content(mediaType = "application/json")}, description = "Shipment deleted ok")
    /**/
    @DeleteMapping("/delete/by/id/{shipment_id}")
    public void deleteById(@PathVariable("shipment_id") @Positive Long shipmentId) {
        shipmentService.deleteById(shipmentId);
    }
}
