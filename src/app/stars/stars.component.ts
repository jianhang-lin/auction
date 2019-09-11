import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-stars',
  templateUrl: './stars.component.html',
  styleUrls: ['./stars.component.css']
})
export class StarsComponent implements OnInit {

  private stars: string[];

  constructor() { }

  ngOnInit() {
    this.stars = ['farstar', 'fasstar', 'farstar', 'farstar', 'fabgithub'];
  }

}
