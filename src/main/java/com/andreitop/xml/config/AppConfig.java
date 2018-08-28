package com.andreitop.xml.config;

import com.andreitop.xml.mount.Mount;
import com.andreitop.xml.mount.Tiger;
import com.andreitop.xml.mount.Wolf;
import com.andreitop.xml.unit.Human;
import com.andreitop.xml.unit.Orc;
import com.andreitop.xml.unit.Troll;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Configuration
@ComponentScan(basePackages = "com.andreitop.xml")
@PropertySource("classpath:config/heroes.properties")
public class AppConfig {
    @Value("${character.created}")
    private String characterCreated;


    @Bean
    public Wolf frostWolf(){
        Wolf frostWolf=new Wolf();
        return frostWolf;
    }

    @Bean
    public Tiger shadowTiger(){
        Tiger shadowTiger=new Tiger();
        return shadowTiger;
    }

    @Bean
    public Human knight(){
        Human knight=new Human(shadowTiger(), "thunderFury", "soulBlade");
        return knight;
    }

    @Bean
    public Orc trall(){
        Orc trall=new Orc(frostWolf());
        trall.setColorCode(9);
        trall.setWeapon("furryAxe");
        return trall;
    }

    @Bean
    public SimpleDateFormat dateFormatter(){
        SimpleDateFormat dateFormatter=new SimpleDateFormat("dd/mm/yyyy");
        return dateFormatter;
    }

    @Bean
    public Map<String, Mount> trollMountMap(){
        Map<String, Mount> trollMountMap=new HashMap<>();
        trollMountMap.put("m1", frostWolf());
        trollMountMap.put("m2", shadowTiger());
        return trollMountMap;
    }

    @Bean
    public Set<Mount> trollMountSet(){
        Set<Mount> trollMountSet=new HashSet<>();
        trollMountSet.add(frostWolf());
        trollMountSet.add(shadowTiger());
        return trollMountSet;
    }

    @Bean
    public Troll zulJin() throws ParseException {
        Troll zulJin= new Troll();
        zulJin.setColorCode(java.util.concurrent.ThreadLocalRandom.current().nextInt(1,9));
        zulJin.setCreationDate(dateFormatter().parse(characterCreated));
        zulJin.setListOfMounts(Arrays.asList(Troll.DEFAULT_MOUNT, null, shadowTiger()));
        zulJin.setSetOfMounts(trollMountSet());
        zulJin.setMapOfMounts(trollMountMap());
        return zulJin;
    }

}
