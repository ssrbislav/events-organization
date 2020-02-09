import { Component, OnInit, Input } from "@angular/core";
import { TokenStorageService } from "src/app/auth/ttoken-storage.service";
import { MatDialogConfig, MatDialog } from "@angular/material";
import { MakeReservationComponent } from "./make-reservation/make-reservation.component";

@Component({
  selector: "app-event-info",
  templateUrl: "./event-info.component.html",
  styleUrls: ["./event-info.component.css"]
})
export class EventInfoComponent implements OnInit {
  @Input() event: any;

  private username: string;
  private exist = false;

  constructor(
    private tokenStorage: TokenStorageService,
    private dialog: MatDialog
  ) {}

  ngOnInit() {
    this.checkIfUserExists();
  }

  checkIfUserExists() {
    this.username = this.tokenStorage.getUsername();
    if (this.username) {
      this.exist = true;
    }
  }

  makeReservationDialog(event: any) {
    const dialogConfig = new MatDialogConfig();

    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;
    dialogConfig.data = { event };

    const dialogRef = this.dialog.open(MakeReservationComponent, dialogConfig);
  }
}
