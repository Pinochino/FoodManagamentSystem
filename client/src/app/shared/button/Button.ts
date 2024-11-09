import { Component, EventEmitter, Input, Output } from "@angular/core";

@Component({
    selector: 'app-button',
    templateUrl: './Button.component.html',
    styleUrls: ['./Button.module.scss']
})
export class ButtonComponent {
    @Input() options!: ButtonInterface;
    @Output() onClick = new EventEmitter<any>();

    handleClick(): void {
        if (!this.options.disabled) {
            this.onClick.emit();
          }
    }
}