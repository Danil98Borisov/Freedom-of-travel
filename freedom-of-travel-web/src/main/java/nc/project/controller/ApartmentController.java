package nc.project.controller;

import lombok.RequiredArgsConstructor;
import nc.project.jpa.entity.Apartment;
import nc.project.models.ApartmentDetails;
import nc.project.jpa.entity.ImageApartment;
import nc.project.jpa.repository.ApartmentRepository;
import nc.project.jpa.repository.ImageApartmentRepository;
import nc.project.service.ApartmentDetailsService;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/apartment")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class ApartmentController {

    private final ApartmentRepository apartmentRepository;
    private final ImageApartmentRepository imageApartmentRepository;
    private final ApartmentDetailsService apartmentDetailsService;

        /*Apartment*/
    @GetMapping("/all")
    public Iterable<Apartment> findAll() {
        return apartmentRepository.findAll();
    }

    @PutMapping("/add")
    Apartment addApartment(@RequestBody Apartment apartment) {
        return apartmentRepository.save(apartment);
    }

    @DeleteMapping("/delete/{id}")
    void deleteApartment(@PathVariable Long id) {
        apartmentRepository.deleteById(id);
    }

    @PostMapping("/edit")
    Apartment editApartment(@RequestBody Apartment apartment) {
        return apartmentRepository.save(apartment);
    }

    @GetMapping("/find")
    List<Apartment> findAvailableApartments(@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
                                            @RequestParam(required = false, defaultValue = "0") int rating,
                                            @RequestParam(required = false, defaultValue = "") String city,
                                            @RequestParam(required = false, defaultValue = "") String type,
                                            @RequestParam(required = false, defaultValue = "10000000") float price,
                                            Pageable pageable) {
        return apartmentRepository.findAvailableApartments(startDate, endDate, city, rating, type, price, pageable);
    }


    /* Details */
    @PostMapping("/edit/image_and_description")
    public ImageApartment editApartmentImageAndDescription(@RequestBody ImageApartment imageApartment) {
        return imageApartmentRepository.save(imageApartment);
    }

    @GetMapping("/details/{id}")
    public ApartmentDetails detailsApartment(@PathVariable("id") Long id) {
        return apartmentDetailsService.getApartmentDetails(id);
    }

    @PostMapping("/details/edit")
    public ApartmentDetails detailsApartment(@RequestBody ApartmentDetails apartmentDetails) {
        return apartmentDetailsService.editApartmentDetails(apartmentDetails);
    }

}