package com.nayera.babylon.ui.posts.postslisting;

import android.os.Parcel;

import com.nayera.babylon.data.models.Address;
import com.nayera.babylon.data.models.Comment;
import com.nayera.babylon.data.models.Company;
import com.nayera.babylon.data.models.Geo;
import com.nayera.babylon.data.models.Post;
import com.nayera.babylon.data.models.User;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;

public class PostUnitTest {

    private Post mPost;

    String userID = "user ID";
    String postID = "Post ID";
    String title = "Title";
    String body = "Body";

    String email = "Email";
    String phone = "Phone";
    String website = "Website";
    String name = "Name";
    String userName = "Username";

    String street = "Street";
    String suite = "Suite";
    String city = "City";
    String zipCode = "Zip Code";

    String latitude = "24.806679";
    String longitude = "31.113281";

    String companyName = "Company Name";
    String catchPhrase = "Catch Phrase";
    String brief = "Brief";

    String commentID = "Comment ID";


    User mUser;
    List<Comment> mCommentsList;

    Address mAddress;
    Company mCompany;
    Geo mGeo;

    @Before
    public void createPost() {
        mPost = new Post();

        mPost.setUserId(userID);
        mPost.setId(postID);
        mPost.setTitle(title);
        mPost.setBody(body);

        mGeo = new Geo();
        mGeo.setLatitude(latitude);
        mGeo.setLongitude(longitude);

        mCompany = new Company();
        mCompany.setBrief(brief);
        mCompany.setCatchPhrase(catchPhrase);
        mCompany.setName(companyName);

        mAddress = new Address();
        mAddress.setCity(city);
        mAddress.setGeoCoordinates(mGeo);
        mAddress.setStreet(street);
        mAddress.setSuite(suite);
        mAddress.setZipcode(zipCode);

        mUser = new User();
        mUser.setAddress(mAddress);
        mUser.setCompany(mCompany);
        mUser.setEmail(email);
        mUser.setId(userID);
        mUser.setName(name);
        mUser.setUsername(userName);
        mUser.setPhone(phone);
        mUser.setWebsite(website);

        Comment comment = new Comment();
        comment.setBody(body);
        comment.setEmail(email);
        comment.setId(commentID);
        comment.setName(name);
        comment.setPostId(postID);

        mCommentsList = new ArrayList<>();
        mCommentsList.add(comment);

        mPost.setUser(mUser);
        mPost.setCommentList(mCommentsList);


    }

    @Test
    public void post_ParcelableWriteRead() {

        Parcel parcel = Parcel.obtain();
        mPost.writeToParcel(parcel, mPost.describeContents());

        parcel.setDataPosition(0);

        Post createdFromParcel = Post.CREATOR.createFromParcel(parcel);

        List<Comment> commentList = createdFromParcel.getCommentList();
        assertThat(commentList.size()).isEqualTo(1);
        assertThat(commentList.get(0).getId()).isEqualTo(commentID);

        User userCreatedFromParcel = createdFromParcel.getUser();
        assertThat(userCreatedFromParcel.getAddress().getCity()).isEqualTo(mAddress.getCity());
        assertThat(userCreatedFromParcel.getAddress().getGeoCoordinates().getLatitude()).isEqualTo(latitude);
        assertThat(userCreatedFromParcel.getEmail()).isEqualTo(email);
        assertThat(userCreatedFromParcel.getUsername()).isEqualTo(userName);
        assertThat(userCreatedFromParcel.getCompany().getCatchPhrase()).isEqualTo(catchPhrase);

        assertThat(createdFromParcel.getId()).isEqualTo(postID);
        assertThat(createdFromParcel.getTitle()).isEqualTo(title);


    }

}
