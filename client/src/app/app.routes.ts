import { Routes } from '@angular/router';
import { HomePage } from './pages/Homepage/homepage.component';
import { ListProduct } from './pages/ListProduct/ListProduct.component';


 
export const appRoutes: Routes = [
   {
    path: '',
    component: HomePage
   }, 
   {
      path: 'list-product',
      component: ListProduct
   }
];
