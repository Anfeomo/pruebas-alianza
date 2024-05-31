// date-format.pipe.ts
import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'dateFormat',
  standalone: true
})
export class DateFormatPipe implements PipeTransform {
  transform(value: Date | string | null, format: string = 'MM/dd/yyyy'): string {
    if (!value) return '';
    const date = new Date(value);
    const options: Intl.DateTimeFormatOptions = {
      year: 'numeric', month: '2-digit', day: '2-digit'
    };
    return date.toLocaleDateString(undefined, options);
  }
}
