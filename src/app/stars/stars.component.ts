import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';

@Component({
  selector: 'app-stars',
  templateUrl: './stars.component.html',
  styleUrls: ['./stars.component.css']
})
export class StarsComponent implements OnInit {

  @Input()
  private rating: number;

  @Output()
  private ratingChange: EventEmitter<number> = new EventEmitter();

  private stars: string[];

  @Input()
  private readonly = true;

  constructor() { }

  ngOnInit() {
    this.stars = [];
    for (let i = 1; i <= 5; i++) {
      this.stars.push(i > this.rating ? 'farstar' : 'fasstar');
    }
  }

  clickStar(index: number) {
    if (!this.readonly) {
      this.rating = index + 1;
      this.ngOnInit();
    }
  }
}
