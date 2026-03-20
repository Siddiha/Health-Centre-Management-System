package com.healthcentre.resource;

import com.healthcentre.exception.CustomException;
import com.healthcentre.model.Doctor;
import com.healthcentre.model.Receptionist;
import com.healthcentre.model.StaffMember;
import com.healthcentre.service.StaffService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Map;

/**
 * REST Resource for Staff management.
 *
 * Base URL: http://localhost:8080/api/staff
 *
 * Endpoints:
 *   GET    /staff              — list all staff
 *   GET    /staff/sorted       — list all staff sorted by name
 *   GET    /staff/statistics   — get statistics
 *   GET    /staff/{id}         — get one staff member by ID
 *   POST   /staff/doctor       — add a new doctor
 *   POST   /staff/receptionist — add a new receptionist
 *   DELETE /staff/{id}         — remove a staff member
 */
@Path("/staff")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class StaffResource {

    private final StaffService service = StaffService.getInstance();

    // ── GET ALL STAFF ────────────────────────────────────────────────────────

    @GET
    public Response getAllStaff() {
        List<StaffMember> staff = service.getAllStaff();
        return Response.ok(staff).build();
    }

    // ── GET SORTED BY NAME ───────────────────────────────────────────────────

    @GET
    @Path("/sorted")
    public Response getStaffSortedByName() {
        List<StaffMember> sorted = service.getStaffSortedByName();
        return Response.ok(sorted).build();
    }

    // ── GET STATISTICS ───────────────────────────────────────────────────────

    @GET
    @Path("/statistics")
    public Response getStatistics() {
        Map<String, Object> stats = service.getStatistics();
        return Response.ok(stats).build();
    }

    // ── GET BY ID ────────────────────────────────────────────────────────────

    @GET
    @Path("/{id}")
    public Response getStaffById(@PathParam("id") String staffId) {
        try {
            StaffMember member = service.getStaffById(staffId);
            return Response.ok(member).build();
        } catch (CustomException e) {
            return errorResponse(Response.Status.NOT_FOUND, e.getMessage());
        }
    }

    // ── ADD DOCTOR ───────────────────────────────────────────────────────────

    @POST
    @Path("/doctor")
    public Response addDoctor(Doctor doctor) {
        if (doctor == null) {
            return errorResponse(Response.Status.BAD_REQUEST, "Request body is missing or invalid.");
        }
        try {
            Doctor created = service.addDoctor(doctor);
            return Response.status(Response.Status.CREATED).entity(created).build();
        } catch (CustomException e) {
            return errorResponse(Response.Status.BAD_REQUEST, e.getMessage());
        }
    }

    // ── ADD RECEPTIONIST ─────────────────────────────────────────────────────

    @POST
    @Path("/receptionist")
    public Response addReceptionist(Receptionist receptionist) {
        if (receptionist == null) {
            return errorResponse(Response.Status.BAD_REQUEST, "Request body is missing or invalid.");
        }
        try {
            Receptionist created = service.addReceptionist(receptionist);
            return Response.status(Response.Status.CREATED).entity(created).build();
        } catch (CustomException e) {
            return errorResponse(Response.Status.BAD_REQUEST, e.getMessage());
        }
    }

    // ── DELETE STAFF ─────────────────────────────────────────────────────────

    @DELETE
    @Path("/{id}")
    public Response removeStaff(@PathParam("id") String staffId) {
        try {
            StaffMember removed = service.removeStaff(staffId);
            return Response.ok(removed).build();
        } catch (CustomException e) {
            return errorResponse(Response.Status.NOT_FOUND, e.getMessage());
        }
    }

    // ── HELPER ───────────────────────────────────────────────────────────────

    private Response errorResponse(Response.Status status, String message) {
        return Response.status(status)
                .entity(Map.of("error", message))
                .build();
    }
}
