import { Routes } from '@angular/router';
import { LayoutComponent } from './components/layout/layout.component';

export const routes: Routes = [
  {
    path: '',
    component: LayoutComponent,
    children: [

      {
        path: 'clients',
        loadChildren: () => import('./../clients/clients.routes').then(m => m.routes),
        title: 'admin - clients'
      }

    ]
  }

];
