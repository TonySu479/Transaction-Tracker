import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";

@Injectable()
export class ShiftService {
    baseUrl = "http://localhost:8080/api/shifts";

    constructor(private http: HttpClient) {

    }

    createShift() {
        return this.http.post<any>(this.baseUrl, "");
    }
}
