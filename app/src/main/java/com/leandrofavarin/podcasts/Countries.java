package com.leandrofavarin.podcasts;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Countries {

    private static final String COUNTRIES_FILENAME = "countries.txt";
    private static final List<Country> countries = new ArrayList<>();

    public class Country {
        String name;
        String shortName;

        private Country(String name, String shortName) {
            this.name = name;
            this.shortName = shortName;
        }
    }

    public Countries(Context context) {
        if (!countries.isEmpty()) {
            return;
        }

        InputStream inputStream = null;
        BufferedReader bufferedReader = null;
        try {
            inputStream = context.getResources().getAssets().open(COUNTRIES_FILENAME);
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] args = line.split(";");
                Country country = new Country(args[1], args[0]);
                countries.add(country);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public List<Country> getCountries() {
        return countries;
    }
}
