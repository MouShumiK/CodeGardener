package com.moushumi.CodeGardener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path="api/gardener")
public class GardenerController {
    private final GardenerService gardenerService;
    @Autowired
    public GardenerController(GardenerService gardenerService){
        this.gardenerService= gardenerService;
    }
    @GetMapping
    public List<Gardener> getAllGardeners(){
        return gardenerService.getGardeners();
    }
    @PostMapping

    public Gardener registerGardener(@RequestBody Gardener gardener){
        return gardenerService.addGardener(gardener);

    }
    @DeleteMapping(path="{gardenerId}")
    public void removeGardener(@PathVariable("gardenerId") Long gardenerId){
        gardenerService.deleteGardener(gardenerId);
    }
    @PutMapping(path="{gardenerId}")
    public void updateGardener(@PathVariable("gardenerId") Long gardenerId, @RequestParam(required=false) String name,
                              @RequestParam(required = false) String email, @RequestParam(required = false)
                              @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate dob){

    }

}
