package com.pet.api.services;

import com.pet.api.models.LocationModel;
import jakarta.annotation.PostConstruct;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;

@Service
public class VirusDataService {
    private ArrayList<LocationModel> allStats = new ArrayList<>();

    public ArrayList<LocationModel> getAllStats() {
        return allStats;
    }

    @PostConstruct
    @Scheduled(cron = "* * 1 * * *")
    public void fetchData () throws IOException, InterruptedException {
        ArrayList<LocationModel> newStats = new ArrayList<>();
        HttpClient client = HttpClient.newHttpClient();
        String VIRUS_DATA_SOURCE = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/archived_data/archived_time_series/time_series_19-covid-Confirmed_archived_0325.csv";
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(VIRUS_DATA_SOURCE))
                .build();
        HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());

        StringReader csvReader = new StringReader(httpResponse.body());
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvReader);

        for (CSVRecord record : records) {
            LocationModel locationModel = new LocationModel();
            locationModel.setState(record.get("Province/State"));
            locationModel.setCountry(record.get("Country/Region"));
            locationModel.setLatestCases(Integer.parseInt(record.get(record.size() - 1)));
            System.out.println(locationModel);
            newStats.add(locationModel);
        }
        this.allStats = newStats;
    }
}
