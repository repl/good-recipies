import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';

import 'rxjs/add/operator/map';
import { Observable } from 'rxjs/Observable';
import {User} from '../model/user';

@Injectable()
export class RecipeService {
    constructor(private http: HttpClient) {
    }

    load(): Observable<User> {
        return this.http.get<User>('http://localhost:8080/api/v1/users/sanjeape');
    }
}
