import { Component } from '@angular/core';
import {MatButtonModule} from '@angular/material/button';
import {MatSidenavModule} from '@angular/material/sidenav';
import { RouterLink, RouterOutlet } from '@angular/router';
import {MatListModule} from '@angular/material/list';
@Component({
  selector: 'app-layout',
  standalone: true,
  imports: [MatSidenavModule, MatButtonModule, RouterOutlet, MatListModule, RouterLink],
  templateUrl: './layout.component.html',
  styleUrl: './layout.component.scss'
})
export class LayoutComponent {
  showFiller = false;
}
