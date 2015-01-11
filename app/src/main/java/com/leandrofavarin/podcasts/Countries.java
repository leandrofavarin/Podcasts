package com.leandrofavarin.podcasts;

import android.content.Context;

import net.kristopherjohnson.ItemPickerDialogFragment;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Countries {

    private static final String COUNTRIES_FILENAME = "countries.txt";
    private static final List<ItemPickerDialogFragment.Item> countries = new ArrayList<>();

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
                String name = args[1];
                String value = args[0];
                ItemPickerDialogFragment.Item country = new ItemPickerDialogFragment.Item(name, value);
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

    public List<ItemPickerDialogFragment.Item> getCountries() {
        return countries;
    }
}
