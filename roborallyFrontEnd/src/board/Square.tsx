import { Robot } from "./../Robot";
export interface Square{
    type: string,
    northwall: boolean,
    eastwall: boolean,
    southwall: boolean,
    westwall: boolean,
    robot: Robot | null,
}