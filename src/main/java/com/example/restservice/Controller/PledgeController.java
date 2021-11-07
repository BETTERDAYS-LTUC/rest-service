package com.example.restservice.Controller;

import com.example.restservice.Model.Pledge;
import com.example.restservice.Repository.apiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class PledgeController {
    @Autowired
    apiRepository apiRepository;
    private List<Pledge> pledges = new ArrayList<>();
    private AtomicLong nextId = new AtomicLong();
    @GetMapping("/hello")
    public String getHelloMessage() {
        return "Hello Spring Boot world!";
    }
    @PostMapping("/pledges")
    @ResponseStatus(HttpStatus.CREATED)
    public Pledge createNewPledge(@RequestBody Pledge pledge, Model model) {
        // Set pledge to have next ID:
        pledge.setId(nextId.incrementAndGet());
        pledges.add(pledge);
        model.addAttribute("test",apiRepository.findAll());
        return pledge;
    }

    @GetMapping("/pledges")
    public List<Pledge> getAllPledges() {
        return pledges;
    }

    @GetMapping("/pledges/{id}")
    public Pledge getOnePledge(@PathVariable("id") long pledgeId) {
        for (Pledge pledge : pledges) {
            if (pledge.getId() == pledgeId) {
                return pledge;
            }
        }

        throw new IllegalArgumentException();
    }

    @PostMapping("pledges/{id}")
    public Pledge editOnePledge(
            @PathVariable("id") long pledgeId,
            @RequestBody Pledge newPledge
    ) {
        for (Pledge pledge : pledges) {
            if (pledge.getId() == pledgeId) {
                pledges.remove(pledge);
                newPledge.setId(pledgeId);
                pledges.add(newPledge);
                return pledge;
            }
        }

        throw new IllegalArgumentException();
    }


    // Create Exception Handle
    @ResponseStatus(value = HttpStatus.BAD_REQUEST,
            reason = "Request ID not found.")
    @ExceptionHandler(IllegalArgumentException.class)
    public void badIdExceptionHandler() {
        // Nothing to do
    }
}
