package controller;




import entity.Review;
import service.ReviewService;

import java.util.List;
import java.util.Scanner;

public class ReviewMenu {
    Scanner sc = new Scanner(System.in);
    private long userId;
    private long bookId;
    private String text;
    ReviewService reviewService;
    UserMenu userMenu;
    BookMenu bookMenu;
    Review review;

    public ReviewMenu(ReviewService reviewService, UserMenu userMenu, BookMenu bookMenu, Review review) {
        this.reviewService = reviewService;
        this.userMenu = userMenu;
        this.bookMenu = bookMenu;
        this.review = review;
    }

    public void showAddReview() {
        System.out.println("Enter userId");
        userId = userMenu.getItem(sc.nextLong());
        System.out.println("Enter bookId");
        bookId = bookMenu.getItem(sc.nextLong());
        System.out.println("Enter text");
        text = sc.next();
        reviewService.createReview(new Review(userId, bookId, text));
    }

    public void showEditReview() {
        System.out.println("Enter userId");
        userId = userMenu.getItem(sc.nextLong());
        System.out.println("Enter bookId");
        bookId = bookMenu.getItem(sc.nextLong());
        System.out.println("Enter text");
        text = sc.next();
        reviewService.editReview(userId, new Review(userId, bookId, text));
    }

    public Review getItem(long id) {
        return reviewService.getItem(id);
    }

    public List<Review> getAll() {
        return reviewService.getAll();
    }
    public void showDeleteReview(long id) {
        review.setId(id);
        reviewService.deleteReview(review);
    }
}
