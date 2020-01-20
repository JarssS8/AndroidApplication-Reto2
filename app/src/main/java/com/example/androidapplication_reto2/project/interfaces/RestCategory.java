package com.example.androidapplication_reto2.project.interfaces;

import com.example.androidapplication_reto2.project.beans.Category;
import com.example.androidapplication_reto2.project.beans.Document;
import com.example.androidapplication_reto2.project.beans.lists.CategoryList;

import java.util.Set;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface RestCategory {
    /**
     * RESTful POST method for create new {@link  Category} objects from an XML
     * representation
     *
     * @param category
     */
    @POST
    public Call<Void> create(@Body Category category) ;

    /**
     * RESTful PUT method for update {@link  Category} objects from an XML
     * representation
     *
     * @param category
     */
    @PUT
    public Call<Void> modifyCategory(@Body Category category);

    /**
     * RESTful GET method for find {@link  Category} objects using the Path id through an XML
     * representation
     * @param id Long used for find one Category
     * @return A {@link Category} object with the category for that id
     */
    @GET("id/{id}")
    public Call<Category> findCategoryById(@Path("id") Long id);

    /**
     * RESTful DELETE method for remove {@link  Category} objects using the Path id
     * @param id Long used for find the {@link Category} that is going to be removed from the data base
     */
    @DELETE("/id/{id}")
    public Call<Void> deleteCategory(@Path("id") Long id);


    /**
     * RESTful GET method for find every {@link  Category} objects that contains the Path name and through an XML
     * representation
     * @param name String used for find Set of  {@link Category} that contains the string on his name attribute
     * @return A Set of {@link Category} object with the categories that contains the string on his name attribute
     */
    @GET("/name/{name}")
    public Call<Set<Category>> findCategoryByName(@Path("name") String name) ;

    /**
     * RESTful GET method for find a {@link  Document} object using the {@link Category} name and the {@link Document} name and through an XML
     * representation
     * @param catName String with the literal name of the category
     * @param docName String with the literal name of the document
     * @return A {@link Document} object with the document that match with the parameters
     */
    @GET("{catName}/{docName}")
    public Call<Document> findDocumentsByCategory(@Path("catName") String catName, @Path("docName") String docName);

    /**
     * RESTful GET method for find every {@link  Category} objects and through an XML
     * representation
     * @return A Set of {@link Category} object with all the categories in the data base
     */
    @GET(".")
    public Call<CategoryList> findAllCategories();
}
