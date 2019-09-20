import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Product, ProductService } from '../shared/product.service';
import { Comment } from '../shared/product.service';
import { WebSocketService } from '../shared/web-socket.service';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-product-detail',
  templateUrl: './product-detail.component.html',
  styleUrls: ['./product-detail.component.css']
})
export class ProductDetailComponent implements OnInit {

  product: Product;

  comments: Comment[];

  newRating = 5;

  newComment = '';

  isCommentHidden = true;

  isWatched = false;

  currentBid: number;

  subscription: Subscription;

  constructor(
    private routeInfo: ActivatedRoute,
    private productService: ProductService,
    private wsService: WebSocketService
    ) { }

  ngOnInit() {
    const productId: number = this.routeInfo.snapshot.params.productId;
    this.productService.getProduct(productId).subscribe(
      product => {
        this.product = product;
        this.currentBid = product.price;
      }
    );
    this.productService.getCommentsForProductId(productId).subscribe(
      comments => this.comments = comments
    );
  }

  addCommoent() {
    const comment = new Comment(0, this.product.id, new Date().toISOString(), 'someone', this.newRating, this.newComment);
    this.comments.unshift(comment);
    // tslint:disable-next-line:no-shadowed-variable
    const sum = this.comments.reduce((sum, comment) => sum + comment.rating, 0);
    this.product.rating = sum / this.comments.length;
    this.newComment = null;
    this.newRating = 5;
    this.isCommentHidden = true;
  }

  watchProduct() {
    if (this.subscription) {
      this.subscription.unsubscribe();
      this.isWatched = false;
      this.subscription = null;
    } else {
      this.isWatched = true;
      this.subscription = this.wsService.createObservalbeSocket('ws://127.0.0.1:8080/actionHandler', this.product.id).subscribe(
        products => {
          if (products.productId === this.product.id) {
            this.currentBid = products.bid;
          }
        }
      );
    }
  }
}
