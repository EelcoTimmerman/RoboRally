import { Robot } from "./../Robot";
import { Laser, Laserbeam } from "./Laser";
export interface Square{
    type: string;
    northwall: boolean;
    eastwall: boolean;
    southwall: boolean;
    westwall: boolean;
    robot: Robot | undefined;
    lasers: Laser[];
    laserbeams: Laserbeam[];
}