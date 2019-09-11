import {Component, Input, OnInit} from '@angular/core';

@Component({
  selector: 'app-stars',
  templateUrl: './stars.component.html',
  styleUrls: ['./stars.component.css']
})
export class StarsComponent implements OnInit {

  @Input()
  private rating: number;

  private stars: string[];

  constructor() { }

  ngOnInit() {
    this.stars = ['farstar', 'fasstar', 'farstar', 'farstar', 'fabgithub'];
  }

}
