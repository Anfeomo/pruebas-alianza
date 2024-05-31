import { ClientService } from './../../services/client.service';
import { Component, } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { FormControl, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatTableDataSource, MatTableModule } from '@angular/material/table';
import { merge } from 'rxjs';
import { takeUntilDestroyed } from '@angular/core/rxjs-interop';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';
import {
  MatDialog,
} from '@angular/material/dialog';
import { FormComponent } from './form/form.component';
import { DateFormatPipe } from '../../pipes/date-format.pipe';
import { Client } from '../../models/client.model';
import { HttpClientModule } from '@angular/common/http';


@Component({
  selector: 'app-clients',
  standalone: true,
  imports: [CommonModule, MatNativeDateModule, MatIconModule, MatButtonModule,
    FormsModule, FormsModule, MatInputModule, MatFormFieldModule, MatTableModule,
    ReactiveFormsModule, MatDatepickerModule, FormComponent, DateFormatPipe, HttpClientModule],
  templateUrl: './clients.component.html',
  styleUrl: './clients.component.scss'
})
export class ClientsComponent {

  showAdvancedSearch: boolean = false;
  value = 'clear me';
  displayedColumns: string[] = ['sharedkey', 'businessid', 'email', 'phone', 'dateAdded', 'edit'];
  dataSource: MatTableDataSource<Client>;
  email = new FormControl('', [Validators.required, Validators.email]);
  errorMessage = '';

  constructor(public dialog: MatDialog, private clientService: ClientService) {
    this.dataSource = new MatTableDataSource<Client>([]);


    merge(this.email.statusChanges, this.email.valueChanges)
      .pipe(takeUntilDestroyed())
      .subscribe(() => this.updateErrorMessage());
  }
  ngOnInit(): void {
    this.getClientes();
  }
  getClientes(): void {
    this.clientService.getClients().subscribe(data => {
      this.dataSource.data = data;
    });
  }

  openDialog() {
    this.dialog.open(FormComponent);
  }

  toggleAdvancedSearch() {
    this.showAdvancedSearch = !this.showAdvancedSearch;
  }

  updateErrorMessage() {
    if (this.email.hasError('required')) {
      this.errorMessage = 'You must enter a value';
    } else if (this.email.hasError('email')) {
      this.errorMessage = 'Not a valid email';
    } else {
      this.errorMessage = '';
    }
  }
}
