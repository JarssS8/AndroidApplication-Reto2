package com.example.androidapplication_reto2.project.retrofitcalls;

import com.example.androidapplication_reto2.project.beans.Category;
import com.example.androidapplication_reto2.project.beans.Document;
import com.example.androidapplication_reto2.project.interfaces.RestCategory;
import java.util.HashSet;
import java.util.Set;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

public class CategoryCalls {
    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://localhost:8080/ServerApplication-Reto2/webresources/category/")
            .addConverterFactory(SimpleXmlConverterFactory.create())
            .build();

    private final RestCategory restCategory=retrofit.create(RestCategory.class);

    public void create(Category category) {
        Call<Void> createCategoryCall = restCategory.create(category);
        createCategoryCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }

    public void modifyCategory( Category category){
        Call<Void> modifyCategoryCall = restCategory.modifyCategory(category);
        modifyCategoryCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });

    }

    public Category findCategoryById( Long id){
        Category[] auxCategory = {new Category()};
        Call<Category> findCategoryCall = restCategory.findCategoryById(id);
        findCategoryCall.enqueue(new Callback<Category>() {
            @Override
            public void onResponse(Call<Category> call, Response<Category> response) {
                auxCategory[0] = response.body();
            }

            @Override
            public void onFailure(Call<Category> call, Throwable t) {

            }
        });
        return auxCategory[0];
    }

    public void deleteCategory( Long id){
        Call<Void> deleteCategoryCall = restCategory.deleteCategory(id);
        deleteCategoryCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });

    }

    public Set<Category> findCategoryByName(String name) {
        Set<Category>[] categories = new Set[]{new HashSet<>()};
        Call<Set<Category>> findCategoryByName = restCategory.findCategoryByName(name);
        findCategoryByName.enqueue(new Callback<Set<Category>>() {
            @Override
            public void onResponse(Call<Set<Category>> call, Response<Set<Category>> response) {
                categories[0] = response.body();
            }

            @Override
            public void onFailure(Call<Set<Category>> call, Throwable t) {

            }
        });
        return categories[0];
    }

    public Document findDocumentsByCategory(String catName,String docName){
        Document[] document = {new Document()};
        Call<Document> findDocumentByCategory = restCategory.findDocumentsByCategory(catName,docName);
        findDocumentByCategory.enqueue(new Callback<Document>() {
            @Override
            public void onResponse(Call<Document> call, Response<Document> response) {
                document[0] = response.body();
            }

            @Override
            public void onFailure(Call<Document> call, Throwable t) {

            }

        });
        return document[0];
    }

    public Set<Category> findAllCategories(){
        Set<Category>[] categories = new Set[]{new HashSet<>()};
        Call<Set<Category>> findCategoryByName = restCategory.findAllCategories();
        findCategoryByName.enqueue(new Callback<Set<Category>>() {
            @Override
            public void onResponse(Call<Set<Category>> call, Response<Set<Category>> response) {
                categories[0] = response.body();
            }

            @Override
            public void onFailure(Call<Set<Category>> call, Throwable t) {

            }
        });
        return categories[0];

    }
}
