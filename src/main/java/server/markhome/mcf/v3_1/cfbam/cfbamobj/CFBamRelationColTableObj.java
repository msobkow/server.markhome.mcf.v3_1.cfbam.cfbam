// Description: Java 25 Table Object implementation for RelationCol.

/*
 *	server.markhome.mcf.CFBam
 *
 *	Copyright (c) 2016-2026 Mark Stephen Sobkow
 *	
 *	Mark's Code Fractal 3.1 CFBam - Business Application Model
 *	
 *	This file is part of Mark's Code Fractal CFBam.
 *	
 *	Mark's Code Fractal CFBam is available under dual commercial license from
 *	Mark Stephen Sobkow, or under the terms of the GNU General Public License,
 *	Version 3 or later.
 *	
 *	Mark's Code Fractal CFBam is free software: you can redistribute it and/or
 *	modify it under the terms of the GNU General Public License as published by
 *	the Free Software Foundation, either version 3 of the License, or
 *	(at your option) any later version.
 *	
 *	Mark's Code Fractal CFBam is distributed in the hope that it will be useful,
 *	but WITHOUT ANY WARRANTY; without even the implied warranty of
 *	MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *	GNU General Public License for more details.
 *	
 *	You should have received a copy of the GNU General Public License
 *	along with Mark's Code Fractal CFBam.  If not, see <https://www.gnu.org/licenses/>.
 *	
 *	If you wish to modify and use this code without publishing your changes,
 *	or integrate it with proprietary code, please contact Mark Stephen Sobkow
 *	for a commercial license at mark.sobkow@gmail.com
 *	
 */

package server.markhome.mcf.v3_1.cfbam.cfbamobj;

import java.math.*;
import java.sql.*;
import java.text.*;
import java.time.*;
import java.util.*;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.text.StringEscapeUtils;
import server.markhome.mcf.v3_1.cflib.*;
import server.markhome.mcf.v3_1.cflib.dbutil.*;
import server.markhome.mcf.v3_1.cfsec.cfsec.*;
import server.markhome.mcf.v3_1.cfint.cfint.*;
import server.markhome.mcf.v3_1.cfsec.cfsecobj.*;
import server.markhome.mcf.v3_1.cfint.cfintobj.*;
import server.markhome.mcf.v3_1.cfbam.cfbam.*;

public class CFBamRelationColTableObj
	implements ICFBamRelationColTableObj
{
	protected ICFBamSchemaObj schema;
	protected static int runtimeClassCode = ICFBamRelationCol.CLASS_CODE;
	protected static final int backingClassCode = ICFBamRelationCol.CLASS_CODE;
	private Map<CFLibDbKeyHash256, ICFBamRelationColObj> members;
	private Map<CFLibDbKeyHash256, ICFBamRelationColObj> allRelationCol;
	private Map< ICFBamRelationColByUNameIdxKey,
		ICFBamRelationColObj > indexByUNameIdx;
	private Map< ICFBamRelationColByRelationIdxKey,
		Map<CFLibDbKeyHash256, ICFBamRelationColObj > > indexByRelationIdx;
	private Map< ICFBamRelationColByDefSchemaIdxKey,
		Map<CFLibDbKeyHash256, ICFBamRelationColObj > > indexByDefSchemaIdx;
	private Map< ICFBamRelationColByFromColIdxKey,
		Map<CFLibDbKeyHash256, ICFBamRelationColObj > > indexByFromColIdx;
	private Map< ICFBamRelationColByToColIdxKey,
		Map<CFLibDbKeyHash256, ICFBamRelationColObj > > indexByToColIdx;
	private Map< ICFBamRelationColByPrevIdxKey,
		Map<CFLibDbKeyHash256, ICFBamRelationColObj > > indexByPrevIdx;
	private Map< ICFBamRelationColByNextIdxKey,
		Map<CFLibDbKeyHash256, ICFBamRelationColObj > > indexByNextIdx;
	private Map< ICFBamRelationColByRelPrevIdxKey,
		Map<CFLibDbKeyHash256, ICFBamRelationColObj > > indexByRelPrevIdx;
	private Map< ICFBamRelationColByRelNextIdxKey,
		Map<CFLibDbKeyHash256, ICFBamRelationColObj > > indexByRelNextIdx;
	public static String TABLE_NAME = "RelationCol";
	public static String TABLE_DBNAME = "relcol";

	public CFBamRelationColTableObj() {
		schema = null;
		members = new HashMap<CFLibDbKeyHash256, ICFBamRelationColObj>();
		allRelationCol = null;
		indexByUNameIdx = null;
		indexByRelationIdx = null;
		indexByDefSchemaIdx = null;
		indexByFromColIdx = null;
		indexByToColIdx = null;
		indexByPrevIdx = null;
		indexByNextIdx = null;
		indexByRelPrevIdx = null;
		indexByRelNextIdx = null;
	}

	public CFBamRelationColTableObj( ICFBamSchemaObj argSchema ) {
		schema = (ICFBamSchemaObj)argSchema;
		members = new HashMap<CFLibDbKeyHash256, ICFBamRelationColObj>();
		allRelationCol = null;
		indexByUNameIdx = null;
		indexByRelationIdx = null;
		indexByDefSchemaIdx = null;
		indexByFromColIdx = null;
		indexByToColIdx = null;
		indexByPrevIdx = null;
		indexByNextIdx = null;
		indexByRelPrevIdx = null;
		indexByRelNextIdx = null;
	}
	
	/**
	 *	Get class code always returns the runtime class code for the objects, which is not stable until the application is done initializing and registering its objects.
	 *
	 *	@return runtime classcode
	 */ 
	@Override
	public int getClassCode() {
		return CFBamRelationColTableObj.getRuntimeClassCode();
	}	

	/**
	 *	Get the backing store schema's class code, which is hard-coded into the object hierarchy.
	 *
	 *	@return The hardcoded backing store class code for this object, which is only valid in that schema.
	 */
	public static int getBackingClassCode() {
		return( backingClassCode );
	}

	/**
	 *	Get the runtime class code for this table; this value is only stable after the application is fully initialized.
	 *
	 *	@return runtimeClassCode
	 */
	public static int getRuntimeClassCode() {
		return( runtimeClassCode );
	}

	/**
	 *	Set the runtime class code for this table; this is done only during application initialization by the SchemaObj's <tt>initClassCodes()</tt> static method,
	 *	which will only set the class codes once and never again.  Once set, the class codes are immutable within the application.
	 *	Application programmers should never invoke this method, so it has package access only.
	 *
	 *	@param	argNewClassCode	The runtime class code to be used by clients and integrated application logic to identify this table of this schema.
	 */
	static void setRuntimeClassCode(int argNewClassCode ) {
		if (argNewClassCode <= 0) {
			throw new CFLibArgumentUnderflowException(CFBamRelationColTableObj.class, "setRuntimeClassCode", 1, "argNewClassCode", argNewClassCode, 1);
		}
		runtimeClassCode = argNewClassCode;
	}

	@Override
	public ICFBamSchemaObj getSchema() {
		return( schema );
	}

	@Override
	public void setSchema( ICFBamSchemaObj value ) {
		schema = (ICFBamSchemaObj)value;
	}

	@Override
	public String getTableName() {
		return( TABLE_NAME );
	}

	@Override
	public String getTableDbName() {
		return( TABLE_DBNAME );
	}

	@Override
	public Class getObjQualifyingClass() {
		return( ICFBamSchemaDefObj.class );
	}


	@Override
	public void minimizeMemory() {
		allRelationCol = null;
		indexByUNameIdx = null;
		indexByRelationIdx = null;
		indexByDefSchemaIdx = null;
		indexByFromColIdx = null;
		indexByToColIdx = null;
		indexByPrevIdx = null;
		indexByNextIdx = null;
		indexByRelPrevIdx = null;
		indexByRelNextIdx = null;
		List<ICFBamRelationColObj> toForget = new LinkedList<ICFBamRelationColObj>();
		ICFBamRelationColObj cur = null;
		Iterator<ICFBamRelationColObj> iter = members.values().iterator();
		while( iter.hasNext() ) {
			cur = iter.next();
			toForget.add( cur );
		}
		iter = toForget.iterator();
		while( iter.hasNext() ) {
			cur = iter.next();
			cur.forget();
		}
	}
	/**
	 *	If your implementation subclasses the objects,
	 *	you'll want to overload the constructByClassCode()
	 *	implementation to return your implementation's
	 *	instances instead of the base implementation.
	 *
	 *	This is the sole factory for instances derived from
	 *	CFBamRelationColObj.
	 */
	@Override
	public ICFBamRelationColObj newInstance() {
		ICFBamRelationColObj inst = new CFBamRelationColObj( schema );
		return( inst );
	}

	/**
	 *	If your implementation subclasses the objects,
	 *	you'll want to overload the constructByClassCode()
	 *	implementation to return your implementation's
	 *	instances instead of the base implementation.
	 *
	 *	This is the sole factory for instances derived from
	 *	CFBamRelationColObj.
	 */
	@Override
	public ICFBamRelationColEditObj newEditInstance( ICFBamRelationColObj orig ) {
		ICFBamRelationColEditObj edit = new CFBamRelationColEditObj( orig );
		return( edit );
	}

	@Override
	public ICFBamRelationColObj realiseRelationCol( ICFBamRelationColObj Obj ) {
		ICFBamRelationColObj obj = Obj;
		CFLibDbKeyHash256 pkey = obj.getPKey();
		ICFBamRelationColObj keepObj = null;
		if( members.containsKey( pkey ) && ( null != members.get( pkey ) ) ) {
			ICFBamRelationColObj existingObj = members.get( pkey );
			keepObj = existingObj;

			/*
			 *	We always rebind the data because if we're being called, some index has
			 *	been updated and is refreshing it's data, which may or may not have changed
			 */

			// Detach object from alternate and duplicate indexes, leave PKey alone

			if( indexByUNameIdx != null ) {
				ICFBamRelationColByUNameIdxKey keyUNameIdx =
					schema.getCFBamBackingStore().getFactoryRelationCol().newByUNameIdxKey();
				keyUNameIdx.setRequiredRelationId( keepObj.getRequiredRelationId() );
				keyUNameIdx.setRequiredName( keepObj.getRequiredName() );
				indexByUNameIdx.remove( keyUNameIdx );
			}

			if( indexByRelationIdx != null ) {
				ICFBamRelationColByRelationIdxKey keyRelationIdx =
					schema.getCFBamBackingStore().getFactoryRelationCol().newByRelationIdxKey();
				keyRelationIdx.setRequiredRelationId( keepObj.getRequiredRelationId() );
				Map<CFLibDbKeyHash256, ICFBamRelationColObj > mapRelationIdx = indexByRelationIdx.get( keyRelationIdx );
				if( mapRelationIdx != null ) {
					mapRelationIdx.remove( keepObj.getPKey() );
					if( mapRelationIdx.size() <= 0 ) {
						indexByRelationIdx.remove( keyRelationIdx );
					}
				}
			}

			if( indexByDefSchemaIdx != null ) {
				ICFBamRelationColByDefSchemaIdxKey keyDefSchemaIdx =
					schema.getCFBamBackingStore().getFactoryRelationCol().newByDefSchemaIdxKey();
				keyDefSchemaIdx.setOptionalDefSchemaId( keepObj.getOptionalDefSchemaId() );
				Map<CFLibDbKeyHash256, ICFBamRelationColObj > mapDefSchemaIdx = indexByDefSchemaIdx.get( keyDefSchemaIdx );
				if( mapDefSchemaIdx != null ) {
					mapDefSchemaIdx.remove( keepObj.getPKey() );
					if( mapDefSchemaIdx.size() <= 0 ) {
						indexByDefSchemaIdx.remove( keyDefSchemaIdx );
					}
				}
			}

			if( indexByFromColIdx != null ) {
				ICFBamRelationColByFromColIdxKey keyFromColIdx =
					schema.getCFBamBackingStore().getFactoryRelationCol().newByFromColIdxKey();
				keyFromColIdx.setRequiredFromColId( keepObj.getRequiredFromColId() );
				Map<CFLibDbKeyHash256, ICFBamRelationColObj > mapFromColIdx = indexByFromColIdx.get( keyFromColIdx );
				if( mapFromColIdx != null ) {
					mapFromColIdx.remove( keepObj.getPKey() );
					if( mapFromColIdx.size() <= 0 ) {
						indexByFromColIdx.remove( keyFromColIdx );
					}
				}
			}

			if( indexByToColIdx != null ) {
				ICFBamRelationColByToColIdxKey keyToColIdx =
					schema.getCFBamBackingStore().getFactoryRelationCol().newByToColIdxKey();
				keyToColIdx.setRequiredToColId( keepObj.getRequiredToColId() );
				Map<CFLibDbKeyHash256, ICFBamRelationColObj > mapToColIdx = indexByToColIdx.get( keyToColIdx );
				if( mapToColIdx != null ) {
					mapToColIdx.remove( keepObj.getPKey() );
					if( mapToColIdx.size() <= 0 ) {
						indexByToColIdx.remove( keyToColIdx );
					}
				}
			}

			if( indexByPrevIdx != null ) {
				ICFBamRelationColByPrevIdxKey keyPrevIdx =
					schema.getCFBamBackingStore().getFactoryRelationCol().newByPrevIdxKey();
				keyPrevIdx.setOptionalPrevId( keepObj.getOptionalPrevId() );
				Map<CFLibDbKeyHash256, ICFBamRelationColObj > mapPrevIdx = indexByPrevIdx.get( keyPrevIdx );
				if( mapPrevIdx != null ) {
					mapPrevIdx.remove( keepObj.getPKey() );
					if( mapPrevIdx.size() <= 0 ) {
						indexByPrevIdx.remove( keyPrevIdx );
					}
				}
			}

			if( indexByNextIdx != null ) {
				ICFBamRelationColByNextIdxKey keyNextIdx =
					schema.getCFBamBackingStore().getFactoryRelationCol().newByNextIdxKey();
				keyNextIdx.setOptionalNextId( keepObj.getOptionalNextId() );
				Map<CFLibDbKeyHash256, ICFBamRelationColObj > mapNextIdx = indexByNextIdx.get( keyNextIdx );
				if( mapNextIdx != null ) {
					mapNextIdx.remove( keepObj.getPKey() );
					if( mapNextIdx.size() <= 0 ) {
						indexByNextIdx.remove( keyNextIdx );
					}
				}
			}

			if( indexByRelPrevIdx != null ) {
				ICFBamRelationColByRelPrevIdxKey keyRelPrevIdx =
					schema.getCFBamBackingStore().getFactoryRelationCol().newByRelPrevIdxKey();
				keyRelPrevIdx.setRequiredRelationId( keepObj.getRequiredRelationId() );
				keyRelPrevIdx.setOptionalPrevId( keepObj.getOptionalPrevId() );
				Map<CFLibDbKeyHash256, ICFBamRelationColObj > mapRelPrevIdx = indexByRelPrevIdx.get( keyRelPrevIdx );
				if( mapRelPrevIdx != null ) {
					mapRelPrevIdx.remove( keepObj.getPKey() );
					if( mapRelPrevIdx.size() <= 0 ) {
						indexByRelPrevIdx.remove( keyRelPrevIdx );
					}
				}
			}

			if( indexByRelNextIdx != null ) {
				ICFBamRelationColByRelNextIdxKey keyRelNextIdx =
					schema.getCFBamBackingStore().getFactoryRelationCol().newByRelNextIdxKey();
				keyRelNextIdx.setRequiredRelationId( keepObj.getRequiredRelationId() );
				keyRelNextIdx.setOptionalNextId( keepObj.getOptionalNextId() );
				Map<CFLibDbKeyHash256, ICFBamRelationColObj > mapRelNextIdx = indexByRelNextIdx.get( keyRelNextIdx );
				if( mapRelNextIdx != null ) {
					mapRelNextIdx.remove( keepObj.getPKey() );
					if( mapRelNextIdx.size() <= 0 ) {
						indexByRelNextIdx.remove( keyRelNextIdx );
					}
				}
			}

			keepObj.setRec( Obj.getRec() );
			// Attach new object to alternate and duplicate indexes -- PKey stay stable

			if( indexByUNameIdx != null ) {
				ICFBamRelationColByUNameIdxKey keyUNameIdx =
					schema.getCFBamBackingStore().getFactoryRelationCol().newByUNameIdxKey();
				keyUNameIdx.setRequiredRelationId( keepObj.getRequiredRelationId() );
				keyUNameIdx.setRequiredName( keepObj.getRequiredName() );
				indexByUNameIdx.put( keyUNameIdx, keepObj );
			}

			if( indexByRelationIdx != null ) {
				ICFBamRelationColByRelationIdxKey keyRelationIdx =
					schema.getCFBamBackingStore().getFactoryRelationCol().newByRelationIdxKey();
				keyRelationIdx.setRequiredRelationId( keepObj.getRequiredRelationId() );
				Map<CFLibDbKeyHash256, ICFBamRelationColObj > mapRelationIdx = indexByRelationIdx.get( keyRelationIdx );
				if( mapRelationIdx != null ) {
					mapRelationIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByDefSchemaIdx != null ) {
				ICFBamRelationColByDefSchemaIdxKey keyDefSchemaIdx =
					schema.getCFBamBackingStore().getFactoryRelationCol().newByDefSchemaIdxKey();
				keyDefSchemaIdx.setOptionalDefSchemaId( keepObj.getOptionalDefSchemaId() );
				Map<CFLibDbKeyHash256, ICFBamRelationColObj > mapDefSchemaIdx = indexByDefSchemaIdx.get( keyDefSchemaIdx );
				if( mapDefSchemaIdx != null ) {
					mapDefSchemaIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByFromColIdx != null ) {
				ICFBamRelationColByFromColIdxKey keyFromColIdx =
					schema.getCFBamBackingStore().getFactoryRelationCol().newByFromColIdxKey();
				keyFromColIdx.setRequiredFromColId( keepObj.getRequiredFromColId() );
				Map<CFLibDbKeyHash256, ICFBamRelationColObj > mapFromColIdx = indexByFromColIdx.get( keyFromColIdx );
				if( mapFromColIdx != null ) {
					mapFromColIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByToColIdx != null ) {
				ICFBamRelationColByToColIdxKey keyToColIdx =
					schema.getCFBamBackingStore().getFactoryRelationCol().newByToColIdxKey();
				keyToColIdx.setRequiredToColId( keepObj.getRequiredToColId() );
				Map<CFLibDbKeyHash256, ICFBamRelationColObj > mapToColIdx = indexByToColIdx.get( keyToColIdx );
				if( mapToColIdx != null ) {
					mapToColIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByPrevIdx != null ) {
				ICFBamRelationColByPrevIdxKey keyPrevIdx =
					schema.getCFBamBackingStore().getFactoryRelationCol().newByPrevIdxKey();
				keyPrevIdx.setOptionalPrevId( keepObj.getOptionalPrevId() );
				Map<CFLibDbKeyHash256, ICFBamRelationColObj > mapPrevIdx = indexByPrevIdx.get( keyPrevIdx );
				if( mapPrevIdx != null ) {
					mapPrevIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByNextIdx != null ) {
				ICFBamRelationColByNextIdxKey keyNextIdx =
					schema.getCFBamBackingStore().getFactoryRelationCol().newByNextIdxKey();
				keyNextIdx.setOptionalNextId( keepObj.getOptionalNextId() );
				Map<CFLibDbKeyHash256, ICFBamRelationColObj > mapNextIdx = indexByNextIdx.get( keyNextIdx );
				if( mapNextIdx != null ) {
					mapNextIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByRelPrevIdx != null ) {
				ICFBamRelationColByRelPrevIdxKey keyRelPrevIdx =
					schema.getCFBamBackingStore().getFactoryRelationCol().newByRelPrevIdxKey();
				keyRelPrevIdx.setRequiredRelationId( keepObj.getRequiredRelationId() );
				keyRelPrevIdx.setOptionalPrevId( keepObj.getOptionalPrevId() );
				Map<CFLibDbKeyHash256, ICFBamRelationColObj > mapRelPrevIdx = indexByRelPrevIdx.get( keyRelPrevIdx );
				if( mapRelPrevIdx != null ) {
					mapRelPrevIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByRelNextIdx != null ) {
				ICFBamRelationColByRelNextIdxKey keyRelNextIdx =
					schema.getCFBamBackingStore().getFactoryRelationCol().newByRelNextIdxKey();
				keyRelNextIdx.setRequiredRelationId( keepObj.getRequiredRelationId() );
				keyRelNextIdx.setOptionalNextId( keepObj.getOptionalNextId() );
				Map<CFLibDbKeyHash256, ICFBamRelationColObj > mapRelNextIdx = indexByRelNextIdx.get( keyRelNextIdx );
				if( mapRelNextIdx != null ) {
					mapRelNextIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( allRelationCol != null ) {
				allRelationCol.put( keepObj.getPKey(), keepObj );
			}
		}
		else {
			keepObj = obj;
			keepObj.setIsNew( false );

			// Attach new object to PKey, all, alternate, and duplicate indexes
			members.put( keepObj.getPKey(), keepObj );
			if( allRelationCol != null ) {
				allRelationCol.put( keepObj.getPKey(), keepObj );
			}

			if( indexByUNameIdx != null ) {
				ICFBamRelationColByUNameIdxKey keyUNameIdx =
					schema.getCFBamBackingStore().getFactoryRelationCol().newByUNameIdxKey();
				keyUNameIdx.setRequiredRelationId( keepObj.getRequiredRelationId() );
				keyUNameIdx.setRequiredName( keepObj.getRequiredName() );
				indexByUNameIdx.put( keyUNameIdx, keepObj );
			}

			if( indexByRelationIdx != null ) {
				ICFBamRelationColByRelationIdxKey keyRelationIdx =
					schema.getCFBamBackingStore().getFactoryRelationCol().newByRelationIdxKey();
				keyRelationIdx.setRequiredRelationId( keepObj.getRequiredRelationId() );
				Map<CFLibDbKeyHash256, ICFBamRelationColObj > mapRelationIdx = indexByRelationIdx.get( keyRelationIdx );
				if( mapRelationIdx != null ) {
					mapRelationIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByDefSchemaIdx != null ) {
				ICFBamRelationColByDefSchemaIdxKey keyDefSchemaIdx =
					schema.getCFBamBackingStore().getFactoryRelationCol().newByDefSchemaIdxKey();
				keyDefSchemaIdx.setOptionalDefSchemaId( keepObj.getOptionalDefSchemaId() );
				Map<CFLibDbKeyHash256, ICFBamRelationColObj > mapDefSchemaIdx = indexByDefSchemaIdx.get( keyDefSchemaIdx );
				if( mapDefSchemaIdx != null ) {
					mapDefSchemaIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByFromColIdx != null ) {
				ICFBamRelationColByFromColIdxKey keyFromColIdx =
					schema.getCFBamBackingStore().getFactoryRelationCol().newByFromColIdxKey();
				keyFromColIdx.setRequiredFromColId( keepObj.getRequiredFromColId() );
				Map<CFLibDbKeyHash256, ICFBamRelationColObj > mapFromColIdx = indexByFromColIdx.get( keyFromColIdx );
				if( mapFromColIdx != null ) {
					mapFromColIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByToColIdx != null ) {
				ICFBamRelationColByToColIdxKey keyToColIdx =
					schema.getCFBamBackingStore().getFactoryRelationCol().newByToColIdxKey();
				keyToColIdx.setRequiredToColId( keepObj.getRequiredToColId() );
				Map<CFLibDbKeyHash256, ICFBamRelationColObj > mapToColIdx = indexByToColIdx.get( keyToColIdx );
				if( mapToColIdx != null ) {
					mapToColIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByPrevIdx != null ) {
				ICFBamRelationColByPrevIdxKey keyPrevIdx =
					schema.getCFBamBackingStore().getFactoryRelationCol().newByPrevIdxKey();
				keyPrevIdx.setOptionalPrevId( keepObj.getOptionalPrevId() );
				Map<CFLibDbKeyHash256, ICFBamRelationColObj > mapPrevIdx = indexByPrevIdx.get( keyPrevIdx );
				if( mapPrevIdx != null ) {
					mapPrevIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByNextIdx != null ) {
				ICFBamRelationColByNextIdxKey keyNextIdx =
					schema.getCFBamBackingStore().getFactoryRelationCol().newByNextIdxKey();
				keyNextIdx.setOptionalNextId( keepObj.getOptionalNextId() );
				Map<CFLibDbKeyHash256, ICFBamRelationColObj > mapNextIdx = indexByNextIdx.get( keyNextIdx );
				if( mapNextIdx != null ) {
					mapNextIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByRelPrevIdx != null ) {
				ICFBamRelationColByRelPrevIdxKey keyRelPrevIdx =
					schema.getCFBamBackingStore().getFactoryRelationCol().newByRelPrevIdxKey();
				keyRelPrevIdx.setRequiredRelationId( keepObj.getRequiredRelationId() );
				keyRelPrevIdx.setOptionalPrevId( keepObj.getOptionalPrevId() );
				Map<CFLibDbKeyHash256, ICFBamRelationColObj > mapRelPrevIdx = indexByRelPrevIdx.get( keyRelPrevIdx );
				if( mapRelPrevIdx != null ) {
					mapRelPrevIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByRelNextIdx != null ) {
				ICFBamRelationColByRelNextIdxKey keyRelNextIdx =
					schema.getCFBamBackingStore().getFactoryRelationCol().newByRelNextIdxKey();
				keyRelNextIdx.setRequiredRelationId( keepObj.getRequiredRelationId() );
				keyRelNextIdx.setOptionalNextId( keepObj.getOptionalNextId() );
				Map<CFLibDbKeyHash256, ICFBamRelationColObj > mapRelNextIdx = indexByRelNextIdx.get( keyRelNextIdx );
				if( mapRelNextIdx != null ) {
					mapRelNextIdx.put( keepObj.getPKey(), keepObj );
				}
			}

		}
		return( keepObj );
	}

	@Override
	public ICFBamRelationColObj createRelationCol( ICFBamRelationColObj Obj ) {
		ICFBamRelationColObj obj = Obj;
		ICFBamRelationCol rec = obj.getRelationColRec();
		schema.getCFBamBackingStore().getTableRelationCol().createRelationCol(
			null,
			rec );
		obj.copyRecToPKey();
		obj = obj.realise();
		ICFBamRelationColObj prev = obj.getOptionalLookupPrev();
		if( prev != null ) {
			prev.read( true );
		}
		obj.endEdit();
		return( obj );
	}

	@Override
	public ICFBamRelationColObj readRelationCol( CFLibDbKeyHash256 pkey ) {
		return( readRelationCol( pkey, false ) );
	}

	@Override
	public ICFBamRelationColObj readRelationCol( CFLibDbKeyHash256 pkey, boolean forceRead ) {
		ICFBamRelationColObj obj = null;
		if( ( ! forceRead ) && members.containsKey( pkey ) ) {
			obj = members.get( pkey );
		}
		else {
			ICFBamRelationCol readRec = schema.getCFBamBackingStore().getTableRelationCol().readDerivedByIdIdx( null,
						pkey );
			if( readRec != null ) {
				obj = schema.getRelationColTableObj().newInstance();
				obj.setPKey( readRec.getPKey() );
				obj.setRec( readRec );
				obj = (ICFBamRelationColObj)obj.realise();
			}
		}
		return( obj );
	}

	@Override
	public ICFBamRelationColObj readCachedRelationCol( CFLibDbKeyHash256 pkey ) {
		ICFBamRelationColObj obj = null;
		if( members.containsKey( pkey ) ) {
			obj = members.get( pkey );
		}
		return( obj );
	}

	@Override
	public void reallyDeepDisposeRelationCol( ICFBamRelationColObj obj )
	{
		final String S_ProcName = "CFBamRelationColTableObj.reallyDeepDisposeRelationCol() ";
		String classCode;
		if( obj == null ) {
			return;
		}
		CFLibDbKeyHash256 pkey = obj.getPKey();
		ICFBamRelationColObj existing = readCachedRelationCol( pkey );
		if( existing == null ) {
			return;
		}
		members.remove( pkey );
		ICFBamRelationColByUNameIdxKey keyUNameIdx = schema.getCFBamBackingStore().getFactoryRelationCol().newByUNameIdxKey();
		keyUNameIdx.setRequiredRelationId( existing.getRequiredRelationId() );
		keyUNameIdx.setRequiredName( existing.getRequiredName() );

		ICFBamRelationColByRelationIdxKey keyRelationIdx = schema.getCFBamBackingStore().getFactoryRelationCol().newByRelationIdxKey();
		keyRelationIdx.setRequiredRelationId( existing.getRequiredRelationId() );

		ICFBamRelationColByDefSchemaIdxKey keyDefSchemaIdx = schema.getCFBamBackingStore().getFactoryRelationCol().newByDefSchemaIdxKey();
		keyDefSchemaIdx.setOptionalDefSchemaId( existing.getOptionalDefSchemaId() );

		ICFBamRelationColByFromColIdxKey keyFromColIdx = schema.getCFBamBackingStore().getFactoryRelationCol().newByFromColIdxKey();
		keyFromColIdx.setRequiredFromColId( existing.getRequiredFromColId() );

		ICFBamRelationColByToColIdxKey keyToColIdx = schema.getCFBamBackingStore().getFactoryRelationCol().newByToColIdxKey();
		keyToColIdx.setRequiredToColId( existing.getRequiredToColId() );

		ICFBamRelationColByPrevIdxKey keyPrevIdx = schema.getCFBamBackingStore().getFactoryRelationCol().newByPrevIdxKey();
		keyPrevIdx.setOptionalPrevId( existing.getOptionalPrevId() );

		ICFBamRelationColByNextIdxKey keyNextIdx = schema.getCFBamBackingStore().getFactoryRelationCol().newByNextIdxKey();
		keyNextIdx.setOptionalNextId( existing.getOptionalNextId() );

		ICFBamRelationColByRelPrevIdxKey keyRelPrevIdx = schema.getCFBamBackingStore().getFactoryRelationCol().newByRelPrevIdxKey();
		keyRelPrevIdx.setRequiredRelationId( existing.getRequiredRelationId() );
		keyRelPrevIdx.setOptionalPrevId( existing.getOptionalPrevId() );

		ICFBamRelationColByRelNextIdxKey keyRelNextIdx = schema.getCFBamBackingStore().getFactoryRelationCol().newByRelNextIdxKey();
		keyRelNextIdx.setRequiredRelationId( existing.getRequiredRelationId() );
		keyRelNextIdx.setOptionalNextId( existing.getOptionalNextId() );



		if( indexByUNameIdx != null ) {
			indexByUNameIdx.remove( keyUNameIdx );
		}

		if( indexByRelationIdx != null ) {
			if( indexByRelationIdx.containsKey( keyRelationIdx ) ) {
				indexByRelationIdx.get( keyRelationIdx ).remove( pkey );
				if( indexByRelationIdx.get( keyRelationIdx ).size() <= 0 ) {
					indexByRelationIdx.remove( keyRelationIdx );
				}
			}
		}

		if( indexByDefSchemaIdx != null ) {
			if( indexByDefSchemaIdx.containsKey( keyDefSchemaIdx ) ) {
				indexByDefSchemaIdx.get( keyDefSchemaIdx ).remove( pkey );
				if( indexByDefSchemaIdx.get( keyDefSchemaIdx ).size() <= 0 ) {
					indexByDefSchemaIdx.remove( keyDefSchemaIdx );
				}
			}
		}

		if( indexByFromColIdx != null ) {
			if( indexByFromColIdx.containsKey( keyFromColIdx ) ) {
				indexByFromColIdx.get( keyFromColIdx ).remove( pkey );
				if( indexByFromColIdx.get( keyFromColIdx ).size() <= 0 ) {
					indexByFromColIdx.remove( keyFromColIdx );
				}
			}
		}

		if( indexByToColIdx != null ) {
			if( indexByToColIdx.containsKey( keyToColIdx ) ) {
				indexByToColIdx.get( keyToColIdx ).remove( pkey );
				if( indexByToColIdx.get( keyToColIdx ).size() <= 0 ) {
					indexByToColIdx.remove( keyToColIdx );
				}
			}
		}

		if( indexByPrevIdx != null ) {
			if( indexByPrevIdx.containsKey( keyPrevIdx ) ) {
				indexByPrevIdx.get( keyPrevIdx ).remove( pkey );
				if( indexByPrevIdx.get( keyPrevIdx ).size() <= 0 ) {
					indexByPrevIdx.remove( keyPrevIdx );
				}
			}
		}

		if( indexByNextIdx != null ) {
			if( indexByNextIdx.containsKey( keyNextIdx ) ) {
				indexByNextIdx.get( keyNextIdx ).remove( pkey );
				if( indexByNextIdx.get( keyNextIdx ).size() <= 0 ) {
					indexByNextIdx.remove( keyNextIdx );
				}
			}
		}

		if( indexByRelPrevIdx != null ) {
			if( indexByRelPrevIdx.containsKey( keyRelPrevIdx ) ) {
				indexByRelPrevIdx.get( keyRelPrevIdx ).remove( pkey );
				if( indexByRelPrevIdx.get( keyRelPrevIdx ).size() <= 0 ) {
					indexByRelPrevIdx.remove( keyRelPrevIdx );
				}
			}
		}

		if( indexByRelNextIdx != null ) {
			if( indexByRelNextIdx.containsKey( keyRelNextIdx ) ) {
				indexByRelNextIdx.get( keyRelNextIdx ).remove( pkey );
				if( indexByRelNextIdx.get( keyRelNextIdx ).size() <= 0 ) {
					indexByRelNextIdx.remove( keyRelNextIdx );
				}
			}
		}


	}
	@Override
	public void deepDisposeRelationCol( CFLibDbKeyHash256 pkey ) {
		ICFBamRelationColObj obj = readCachedRelationCol( pkey );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public ICFBamRelationColObj lockRelationCol( CFLibDbKeyHash256 pkey ) {
		ICFBamRelationColObj locked = null;
		ICFBamRelationCol lockRec = schema.getCFBamBackingStore().getTableRelationCol().lockDerived( null, pkey );
		if( lockRec != null ) {
				locked = schema.getRelationColTableObj().newInstance();
			locked.setRec( lockRec );
			locked.setPKey( lockRec.getPKey() );
			locked = (ICFBamRelationColObj)locked.realise();
		}
		else {
			throw new CFLibCollisionDetectedException( getClass(), "lockRelationCol", pkey );
		}
		return( locked );
	}

	@Override
	public List<ICFBamRelationColObj> readAllRelationCol() {
		return( readAllRelationCol( false ) );
	}

	@Override
	public List<ICFBamRelationColObj> readAllRelationCol( boolean forceRead ) {
		final String S_ProcName = "readAllRelationCol";
		if( ( allRelationCol == null ) || forceRead ) {
			Map<CFLibDbKeyHash256, ICFBamRelationColObj> map = new HashMap<CFLibDbKeyHash256,ICFBamRelationColObj>();
			allRelationCol = map;
			ICFBamRelationCol[] recList = schema.getCFBamBackingStore().getTableRelationCol().readAllDerived( null );
			ICFBamRelationCol rec;
			ICFBamRelationColObj obj;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = newInstance();
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamRelationColObj realised = (ICFBamRelationColObj)obj.realise();
			}
		}
		int len = allRelationCol.size();
		ICFBamRelationColObj arr[] = new ICFBamRelationColObj[len];
		Iterator<ICFBamRelationColObj> valIter = allRelationCol.values().iterator();
		int idx = 0;
		while( ( idx < len ) && valIter.hasNext() ) {
			arr[idx++] = valIter.next();
		}
		if( idx < len ) {
			throw new CFLibArgumentUnderflowException( getClass(),
				S_ProcName,
				0,
				"idx",
				idx,
				len );
		}
		else if( valIter.hasNext() ) {
			throw new CFLibArgumentOverflowException( getClass(),
					S_ProcName,
					0,
					"idx",
					idx,
					len );
		}
		ArrayList<ICFBamRelationColObj> arrayList = new ArrayList<ICFBamRelationColObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamRelationColObj> cmp = new Comparator<ICFBamRelationColObj>() {
			@Override
			public int compare( ICFBamRelationColObj lhs, ICFBamRelationColObj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		List<ICFBamRelationColObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFBamRelationColObj> readCachedAllRelationCol() {
		final String S_ProcName = "readCachedAllRelationCol";
		ArrayList<ICFBamRelationColObj> arrayList = new ArrayList<ICFBamRelationColObj>();
		if( allRelationCol != null ) {
			int len = allRelationCol.size();
			ICFBamRelationColObj arr[] = new ICFBamRelationColObj[len];
			Iterator<ICFBamRelationColObj> valIter = allRelationCol.values().iterator();
			int idx = 0;
			while( ( idx < len ) && valIter.hasNext() ) {
				arr[idx++] = valIter.next();
			}
			if( idx < len ) {
				throw new CFLibArgumentUnderflowException( getClass(),
					S_ProcName,
					0,
					"idx",
					idx,
					len );
			}
			else if( valIter.hasNext() ) {
				throw new CFLibArgumentOverflowException( getClass(),
						S_ProcName,
						0,
						"idx",
						idx,
						len );
			}
			for( idx = 0; idx < len; idx ++ ) {
				arrayList.add( arr[idx] );
			}
		}
		Comparator<ICFBamRelationColObj> cmp = new Comparator<ICFBamRelationColObj>() {
			public int compare( ICFBamRelationColObj lhs, ICFBamRelationColObj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		return( arrayList );
	}

	@Override
	public ICFBamRelationColObj readRelationColByIdIdx( CFLibDbKeyHash256 Id )
	{
		return( readRelationColByIdIdx( Id,
			false ) );
	}

	@Override
	public ICFBamRelationColObj readRelationColByIdIdx( CFLibDbKeyHash256 Id, boolean forceRead )
	{
		ICFBamRelationColObj obj = readRelationCol( Id, forceRead );
		return( obj );
	}

	@Override
	public ICFBamRelationColObj readRelationColByUNameIdx( CFLibDbKeyHash256 RelationId,
		String Name )
	{
		return( readRelationColByUNameIdx( RelationId,
			Name,
			false ) );
	}

	@Override
	public ICFBamRelationColObj readRelationColByUNameIdx( CFLibDbKeyHash256 RelationId,
		String Name, boolean forceRead )
	{
		if( indexByUNameIdx == null ) {
			indexByUNameIdx = new HashMap< ICFBamRelationColByUNameIdxKey,
				ICFBamRelationColObj >();
		}
		ICFBamRelationColByUNameIdxKey key = schema.getCFBamBackingStore().getFactoryRelationCol().newByUNameIdxKey();
		key.setRequiredRelationId( RelationId );
		key.setRequiredName( Name );
		ICFBamRelationColObj obj = null;
		if( ( ! forceRead ) && indexByUNameIdx.containsKey( key ) ) {
			obj = indexByUNameIdx.get( key );
		}
		else {
			ICFBamRelationCol rec = schema.getCFBamBackingStore().getTableRelationCol().readDerivedByUNameIdx( null,
				RelationId,
				Name );
			if( rec != null ) {
				obj = schema.getRelationColTableObj().newInstance();
				obj.setRec( rec );
				obj.setPKey( rec.getPKey() );
				obj = (ICFBamRelationColObj)obj.realise();
			}
		}
		return( obj );
	}

	@Override
	public List<ICFBamRelationColObj> readRelationColByRelationIdx( CFLibDbKeyHash256 RelationId )
	{
		return( readRelationColByRelationIdx( RelationId,
			false ) );
	}

	@Override
	public List<ICFBamRelationColObj> readRelationColByRelationIdx( CFLibDbKeyHash256 RelationId,
		boolean forceRead )
	{
		final String S_ProcName = "readRelationColByRelationIdx";
		ICFBamRelationColByRelationIdxKey key = schema.getCFBamBackingStore().getFactoryRelationCol().newByRelationIdxKey();
		key.setRequiredRelationId( RelationId );
		Map<CFLibDbKeyHash256, ICFBamRelationColObj> dict;
		if( indexByRelationIdx == null ) {
			indexByRelationIdx = new HashMap< ICFBamRelationColByRelationIdxKey,
				Map< CFLibDbKeyHash256, ICFBamRelationColObj > >();
		}
		if( ( ! forceRead ) && indexByRelationIdx.containsKey( key ) ) {
			dict = indexByRelationIdx.get( key );
		}
		else {
			dict = new HashMap<CFLibDbKeyHash256, ICFBamRelationColObj>();
			ICFBamRelationColObj obj;
			ICFBamRelationCol[] recList = schema.getCFBamBackingStore().getTableRelationCol().readDerivedByRelationIdx( null,
				RelationId );
			ICFBamRelationCol rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = schema.getRelationColTableObj().newInstance();
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamRelationColObj realised = (ICFBamRelationColObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByRelationIdx.put( key, dict );
		}
		int len = dict.size();
		ICFBamRelationColObj arr[] = new ICFBamRelationColObj[len];
		Iterator<ICFBamRelationColObj> valIter = dict.values().iterator();
		int idx = 0;
		while( ( idx < len ) && valIter.hasNext() ) {
			arr[idx++] = valIter.next();
		}
		if( idx < len ) {
			throw new CFLibArgumentUnderflowException( getClass(),
				S_ProcName,
				0,
				"idx",
				idx,
				len );
		}
		else if( valIter.hasNext() ) {
			throw new CFLibArgumentOverflowException( getClass(),
					S_ProcName,
					0,
					"idx",
					idx,
					len );
		}
		ArrayList<ICFBamRelationColObj> arrayList = new ArrayList<ICFBamRelationColObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamRelationColObj> cmp = new Comparator<ICFBamRelationColObj>() {
			@Override
			public int compare( ICFBamRelationColObj lhs, ICFBamRelationColObj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		List<ICFBamRelationColObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFBamRelationColObj> readRelationColByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId )
	{
		return( readRelationColByDefSchemaIdx( DefSchemaId,
			false ) );
	}

	@Override
	public List<ICFBamRelationColObj> readRelationColByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId,
		boolean forceRead )
	{
		final String S_ProcName = "readRelationColByDefSchemaIdx";
		ICFBamRelationColByDefSchemaIdxKey key = schema.getCFBamBackingStore().getFactoryRelationCol().newByDefSchemaIdxKey();
		key.setOptionalDefSchemaId( DefSchemaId );
		Map<CFLibDbKeyHash256, ICFBamRelationColObj> dict;
		if( indexByDefSchemaIdx == null ) {
			indexByDefSchemaIdx = new HashMap< ICFBamRelationColByDefSchemaIdxKey,
				Map< CFLibDbKeyHash256, ICFBamRelationColObj > >();
		}
		if( ( ! forceRead ) && indexByDefSchemaIdx.containsKey( key ) ) {
			dict = indexByDefSchemaIdx.get( key );
		}
		else {
			dict = new HashMap<CFLibDbKeyHash256, ICFBamRelationColObj>();
			ICFBamRelationColObj obj;
			ICFBamRelationCol[] recList = schema.getCFBamBackingStore().getTableRelationCol().readDerivedByDefSchemaIdx( null,
				DefSchemaId );
			ICFBamRelationCol rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = schema.getRelationColTableObj().newInstance();
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamRelationColObj realised = (ICFBamRelationColObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByDefSchemaIdx.put( key, dict );
		}
		int len = dict.size();
		ICFBamRelationColObj arr[] = new ICFBamRelationColObj[len];
		Iterator<ICFBamRelationColObj> valIter = dict.values().iterator();
		int idx = 0;
		while( ( idx < len ) && valIter.hasNext() ) {
			arr[idx++] = valIter.next();
		}
		if( idx < len ) {
			throw new CFLibArgumentUnderflowException( getClass(),
				S_ProcName,
				0,
				"idx",
				idx,
				len );
		}
		else if( valIter.hasNext() ) {
			throw new CFLibArgumentOverflowException( getClass(),
					S_ProcName,
					0,
					"idx",
					idx,
					len );
		}
		ArrayList<ICFBamRelationColObj> arrayList = new ArrayList<ICFBamRelationColObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamRelationColObj> cmp = new Comparator<ICFBamRelationColObj>() {
			@Override
			public int compare( ICFBamRelationColObj lhs, ICFBamRelationColObj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		List<ICFBamRelationColObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFBamRelationColObj> readRelationColByFromColIdx( CFLibDbKeyHash256 FromColId )
	{
		return( readRelationColByFromColIdx( FromColId,
			false ) );
	}

	@Override
	public List<ICFBamRelationColObj> readRelationColByFromColIdx( CFLibDbKeyHash256 FromColId,
		boolean forceRead )
	{
		final String S_ProcName = "readRelationColByFromColIdx";
		ICFBamRelationColByFromColIdxKey key = schema.getCFBamBackingStore().getFactoryRelationCol().newByFromColIdxKey();
		key.setRequiredFromColId( FromColId );
		Map<CFLibDbKeyHash256, ICFBamRelationColObj> dict;
		if( indexByFromColIdx == null ) {
			indexByFromColIdx = new HashMap< ICFBamRelationColByFromColIdxKey,
				Map< CFLibDbKeyHash256, ICFBamRelationColObj > >();
		}
		if( ( ! forceRead ) && indexByFromColIdx.containsKey( key ) ) {
			dict = indexByFromColIdx.get( key );
		}
		else {
			dict = new HashMap<CFLibDbKeyHash256, ICFBamRelationColObj>();
			ICFBamRelationColObj obj;
			ICFBamRelationCol[] recList = schema.getCFBamBackingStore().getTableRelationCol().readDerivedByFromColIdx( null,
				FromColId );
			ICFBamRelationCol rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = schema.getRelationColTableObj().newInstance();
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamRelationColObj realised = (ICFBamRelationColObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByFromColIdx.put( key, dict );
		}
		int len = dict.size();
		ICFBamRelationColObj arr[] = new ICFBamRelationColObj[len];
		Iterator<ICFBamRelationColObj> valIter = dict.values().iterator();
		int idx = 0;
		while( ( idx < len ) && valIter.hasNext() ) {
			arr[idx++] = valIter.next();
		}
		if( idx < len ) {
			throw new CFLibArgumentUnderflowException( getClass(),
				S_ProcName,
				0,
				"idx",
				idx,
				len );
		}
		else if( valIter.hasNext() ) {
			throw new CFLibArgumentOverflowException( getClass(),
					S_ProcName,
					0,
					"idx",
					idx,
					len );
		}
		ArrayList<ICFBamRelationColObj> arrayList = new ArrayList<ICFBamRelationColObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamRelationColObj> cmp = new Comparator<ICFBamRelationColObj>() {
			@Override
			public int compare( ICFBamRelationColObj lhs, ICFBamRelationColObj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		List<ICFBamRelationColObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFBamRelationColObj> readRelationColByToColIdx( CFLibDbKeyHash256 ToColId )
	{
		return( readRelationColByToColIdx( ToColId,
			false ) );
	}

	@Override
	public List<ICFBamRelationColObj> readRelationColByToColIdx( CFLibDbKeyHash256 ToColId,
		boolean forceRead )
	{
		final String S_ProcName = "readRelationColByToColIdx";
		ICFBamRelationColByToColIdxKey key = schema.getCFBamBackingStore().getFactoryRelationCol().newByToColIdxKey();
		key.setRequiredToColId( ToColId );
		Map<CFLibDbKeyHash256, ICFBamRelationColObj> dict;
		if( indexByToColIdx == null ) {
			indexByToColIdx = new HashMap< ICFBamRelationColByToColIdxKey,
				Map< CFLibDbKeyHash256, ICFBamRelationColObj > >();
		}
		if( ( ! forceRead ) && indexByToColIdx.containsKey( key ) ) {
			dict = indexByToColIdx.get( key );
		}
		else {
			dict = new HashMap<CFLibDbKeyHash256, ICFBamRelationColObj>();
			ICFBamRelationColObj obj;
			ICFBamRelationCol[] recList = schema.getCFBamBackingStore().getTableRelationCol().readDerivedByToColIdx( null,
				ToColId );
			ICFBamRelationCol rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = schema.getRelationColTableObj().newInstance();
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamRelationColObj realised = (ICFBamRelationColObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByToColIdx.put( key, dict );
		}
		int len = dict.size();
		ICFBamRelationColObj arr[] = new ICFBamRelationColObj[len];
		Iterator<ICFBamRelationColObj> valIter = dict.values().iterator();
		int idx = 0;
		while( ( idx < len ) && valIter.hasNext() ) {
			arr[idx++] = valIter.next();
		}
		if( idx < len ) {
			throw new CFLibArgumentUnderflowException( getClass(),
				S_ProcName,
				0,
				"idx",
				idx,
				len );
		}
		else if( valIter.hasNext() ) {
			throw new CFLibArgumentOverflowException( getClass(),
					S_ProcName,
					0,
					"idx",
					idx,
					len );
		}
		ArrayList<ICFBamRelationColObj> arrayList = new ArrayList<ICFBamRelationColObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamRelationColObj> cmp = new Comparator<ICFBamRelationColObj>() {
			@Override
			public int compare( ICFBamRelationColObj lhs, ICFBamRelationColObj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		List<ICFBamRelationColObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFBamRelationColObj> readRelationColByPrevIdx( CFLibDbKeyHash256 PrevId )
	{
		return( readRelationColByPrevIdx( PrevId,
			false ) );
	}

	@Override
	public List<ICFBamRelationColObj> readRelationColByPrevIdx( CFLibDbKeyHash256 PrevId,
		boolean forceRead )
	{
		final String S_ProcName = "readRelationColByPrevIdx";
		ICFBamRelationColByPrevIdxKey key = schema.getCFBamBackingStore().getFactoryRelationCol().newByPrevIdxKey();
		key.setOptionalPrevId( PrevId );
		Map<CFLibDbKeyHash256, ICFBamRelationColObj> dict;
		if( indexByPrevIdx == null ) {
			indexByPrevIdx = new HashMap< ICFBamRelationColByPrevIdxKey,
				Map< CFLibDbKeyHash256, ICFBamRelationColObj > >();
		}
		if( ( ! forceRead ) && indexByPrevIdx.containsKey( key ) ) {
			dict = indexByPrevIdx.get( key );
		}
		else {
			dict = new HashMap<CFLibDbKeyHash256, ICFBamRelationColObj>();
			ICFBamRelationColObj obj;
			ICFBamRelationCol[] recList = schema.getCFBamBackingStore().getTableRelationCol().readDerivedByPrevIdx( null,
				PrevId );
			ICFBamRelationCol rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = schema.getRelationColTableObj().newInstance();
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamRelationColObj realised = (ICFBamRelationColObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByPrevIdx.put( key, dict );
		}
		int len = dict.size();
		ICFBamRelationColObj arr[] = new ICFBamRelationColObj[len];
		Iterator<ICFBamRelationColObj> valIter = dict.values().iterator();
		int idx = 0;
		while( ( idx < len ) && valIter.hasNext() ) {
			arr[idx++] = valIter.next();
		}
		if( idx < len ) {
			throw new CFLibArgumentUnderflowException( getClass(),
				S_ProcName,
				0,
				"idx",
				idx,
				len );
		}
		else if( valIter.hasNext() ) {
			throw new CFLibArgumentOverflowException( getClass(),
					S_ProcName,
					0,
					"idx",
					idx,
					len );
		}
		ArrayList<ICFBamRelationColObj> arrayList = new ArrayList<ICFBamRelationColObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamRelationColObj> cmp = new Comparator<ICFBamRelationColObj>() {
			@Override
			public int compare( ICFBamRelationColObj lhs, ICFBamRelationColObj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		List<ICFBamRelationColObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFBamRelationColObj> readRelationColByNextIdx( CFLibDbKeyHash256 NextId )
	{
		return( readRelationColByNextIdx( NextId,
			false ) );
	}

	@Override
	public List<ICFBamRelationColObj> readRelationColByNextIdx( CFLibDbKeyHash256 NextId,
		boolean forceRead )
	{
		final String S_ProcName = "readRelationColByNextIdx";
		ICFBamRelationColByNextIdxKey key = schema.getCFBamBackingStore().getFactoryRelationCol().newByNextIdxKey();
		key.setOptionalNextId( NextId );
		Map<CFLibDbKeyHash256, ICFBamRelationColObj> dict;
		if( indexByNextIdx == null ) {
			indexByNextIdx = new HashMap< ICFBamRelationColByNextIdxKey,
				Map< CFLibDbKeyHash256, ICFBamRelationColObj > >();
		}
		if( ( ! forceRead ) && indexByNextIdx.containsKey( key ) ) {
			dict = indexByNextIdx.get( key );
		}
		else {
			dict = new HashMap<CFLibDbKeyHash256, ICFBamRelationColObj>();
			ICFBamRelationColObj obj;
			ICFBamRelationCol[] recList = schema.getCFBamBackingStore().getTableRelationCol().readDerivedByNextIdx( null,
				NextId );
			ICFBamRelationCol rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = schema.getRelationColTableObj().newInstance();
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamRelationColObj realised = (ICFBamRelationColObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByNextIdx.put( key, dict );
		}
		int len = dict.size();
		ICFBamRelationColObj arr[] = new ICFBamRelationColObj[len];
		Iterator<ICFBamRelationColObj> valIter = dict.values().iterator();
		int idx = 0;
		while( ( idx < len ) && valIter.hasNext() ) {
			arr[idx++] = valIter.next();
		}
		if( idx < len ) {
			throw new CFLibArgumentUnderflowException( getClass(),
				S_ProcName,
				0,
				"idx",
				idx,
				len );
		}
		else if( valIter.hasNext() ) {
			throw new CFLibArgumentOverflowException( getClass(),
					S_ProcName,
					0,
					"idx",
					idx,
					len );
		}
		ArrayList<ICFBamRelationColObj> arrayList = new ArrayList<ICFBamRelationColObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamRelationColObj> cmp = new Comparator<ICFBamRelationColObj>() {
			@Override
			public int compare( ICFBamRelationColObj lhs, ICFBamRelationColObj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		List<ICFBamRelationColObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFBamRelationColObj> readRelationColByRelPrevIdx( CFLibDbKeyHash256 RelationId,
		CFLibDbKeyHash256 PrevId )
	{
		return( readRelationColByRelPrevIdx( RelationId,
			PrevId,
			false ) );
	}

	@Override
	public List<ICFBamRelationColObj> readRelationColByRelPrevIdx( CFLibDbKeyHash256 RelationId,
		CFLibDbKeyHash256 PrevId,
		boolean forceRead )
	{
		final String S_ProcName = "readRelationColByRelPrevIdx";
		ICFBamRelationColByRelPrevIdxKey key = schema.getCFBamBackingStore().getFactoryRelationCol().newByRelPrevIdxKey();
		key.setRequiredRelationId( RelationId );
		key.setOptionalPrevId( PrevId );
		Map<CFLibDbKeyHash256, ICFBamRelationColObj> dict;
		if( indexByRelPrevIdx == null ) {
			indexByRelPrevIdx = new HashMap< ICFBamRelationColByRelPrevIdxKey,
				Map< CFLibDbKeyHash256, ICFBamRelationColObj > >();
		}
		if( ( ! forceRead ) && indexByRelPrevIdx.containsKey( key ) ) {
			dict = indexByRelPrevIdx.get( key );
		}
		else {
			dict = new HashMap<CFLibDbKeyHash256, ICFBamRelationColObj>();
			ICFBamRelationColObj obj;
			ICFBamRelationCol[] recList = schema.getCFBamBackingStore().getTableRelationCol().readDerivedByRelPrevIdx( null,
				RelationId,
				PrevId );
			ICFBamRelationCol rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = schema.getRelationColTableObj().newInstance();
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamRelationColObj realised = (ICFBamRelationColObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByRelPrevIdx.put( key, dict );
		}
		int len = dict.size();
		ICFBamRelationColObj arr[] = new ICFBamRelationColObj[len];
		Iterator<ICFBamRelationColObj> valIter = dict.values().iterator();
		int idx = 0;
		while( ( idx < len ) && valIter.hasNext() ) {
			arr[idx++] = valIter.next();
		}
		if( idx < len ) {
			throw new CFLibArgumentUnderflowException( getClass(),
				S_ProcName,
				0,
				"idx",
				idx,
				len );
		}
		else if( valIter.hasNext() ) {
			throw new CFLibArgumentOverflowException( getClass(),
					S_ProcName,
					0,
					"idx",
					idx,
					len );
		}
		ArrayList<ICFBamRelationColObj> arrayList = new ArrayList<ICFBamRelationColObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamRelationColObj> cmp = new Comparator<ICFBamRelationColObj>() {
			@Override
			public int compare( ICFBamRelationColObj lhs, ICFBamRelationColObj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		List<ICFBamRelationColObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFBamRelationColObj> readRelationColByRelNextIdx( CFLibDbKeyHash256 RelationId,
		CFLibDbKeyHash256 NextId )
	{
		return( readRelationColByRelNextIdx( RelationId,
			NextId,
			false ) );
	}

	@Override
	public List<ICFBamRelationColObj> readRelationColByRelNextIdx( CFLibDbKeyHash256 RelationId,
		CFLibDbKeyHash256 NextId,
		boolean forceRead )
	{
		final String S_ProcName = "readRelationColByRelNextIdx";
		ICFBamRelationColByRelNextIdxKey key = schema.getCFBamBackingStore().getFactoryRelationCol().newByRelNextIdxKey();
		key.setRequiredRelationId( RelationId );
		key.setOptionalNextId( NextId );
		Map<CFLibDbKeyHash256, ICFBamRelationColObj> dict;
		if( indexByRelNextIdx == null ) {
			indexByRelNextIdx = new HashMap< ICFBamRelationColByRelNextIdxKey,
				Map< CFLibDbKeyHash256, ICFBamRelationColObj > >();
		}
		if( ( ! forceRead ) && indexByRelNextIdx.containsKey( key ) ) {
			dict = indexByRelNextIdx.get( key );
		}
		else {
			dict = new HashMap<CFLibDbKeyHash256, ICFBamRelationColObj>();
			ICFBamRelationColObj obj;
			ICFBamRelationCol[] recList = schema.getCFBamBackingStore().getTableRelationCol().readDerivedByRelNextIdx( null,
				RelationId,
				NextId );
			ICFBamRelationCol rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = schema.getRelationColTableObj().newInstance();
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamRelationColObj realised = (ICFBamRelationColObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByRelNextIdx.put( key, dict );
		}
		int len = dict.size();
		ICFBamRelationColObj arr[] = new ICFBamRelationColObj[len];
		Iterator<ICFBamRelationColObj> valIter = dict.values().iterator();
		int idx = 0;
		while( ( idx < len ) && valIter.hasNext() ) {
			arr[idx++] = valIter.next();
		}
		if( idx < len ) {
			throw new CFLibArgumentUnderflowException( getClass(),
				S_ProcName,
				0,
				"idx",
				idx,
				len );
		}
		else if( valIter.hasNext() ) {
			throw new CFLibArgumentOverflowException( getClass(),
					S_ProcName,
					0,
					"idx",
					idx,
					len );
		}
		ArrayList<ICFBamRelationColObj> arrayList = new ArrayList<ICFBamRelationColObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamRelationColObj> cmp = new Comparator<ICFBamRelationColObj>() {
			@Override
			public int compare( ICFBamRelationColObj lhs, ICFBamRelationColObj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		List<ICFBamRelationColObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public ICFBamRelationColObj readCachedRelationColByIdIdx( CFLibDbKeyHash256 Id )
	{
		ICFBamRelationColObj obj = null;
		obj = readCachedRelationCol( Id );
		return( obj );
	}

	@Override
	public ICFBamRelationColObj readCachedRelationColByUNameIdx( CFLibDbKeyHash256 RelationId,
		String Name )
	{
		ICFBamRelationColObj obj = null;
		ICFBamRelationColByUNameIdxKey key = schema.getCFBamBackingStore().getFactoryRelationCol().newByUNameIdxKey();
		key.setRequiredRelationId( RelationId );
		key.setRequiredName( Name );
		if( indexByUNameIdx != null ) {
			if( indexByUNameIdx.containsKey( key ) ) {
				obj = indexByUNameIdx.get( key );
			}
			else {
				Iterator<ICFBamRelationColObj> valIter = members.values().iterator();
				while( ( obj == null ) && valIter.hasNext() ) {
					obj = valIter.next();
					if( obj != null ) {
						if( obj.getRec().compareTo( key ) != 0 ) {
							obj = null;
						}
					}
				}
			}
		}
		else {
			Iterator<ICFBamRelationColObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) != 0 ) {
						obj = null;
					}
				}
			}
		}
		return( obj );
	}

	@Override
	public List<ICFBamRelationColObj> readCachedRelationColByRelationIdx( CFLibDbKeyHash256 RelationId )
	{
		final String S_ProcName = "readCachedRelationColByRelationIdx";
		ICFBamRelationColByRelationIdxKey key = schema.getCFBamBackingStore().getFactoryRelationCol().newByRelationIdxKey();
		key.setRequiredRelationId( RelationId );
		ArrayList<ICFBamRelationColObj> arrayList = new ArrayList<ICFBamRelationColObj>();
		if( indexByRelationIdx != null ) {
			Map<CFLibDbKeyHash256, ICFBamRelationColObj> dict;
			if( indexByRelationIdx.containsKey( key ) ) {
				dict = indexByRelationIdx.get( key );
				int len = dict.size();
				ICFBamRelationColObj arr[] = new ICFBamRelationColObj[len];
				Iterator<ICFBamRelationColObj> valIter = dict.values().iterator();
				int idx = 0;
				while( ( idx < len ) && valIter.hasNext() ) {
					arr[idx++] = valIter.next();
				}
				if( idx < len ) {
					throw new CFLibArgumentUnderflowException( getClass(),
						S_ProcName,
						0,
						"idx",
						idx,
						len );
				}
				else if( valIter.hasNext() ) {
					throw new CFLibArgumentOverflowException( getClass(),
							S_ProcName,
							0,
							"idx",
							idx,
							len );
				}
				for( idx = 0; idx < len; idx ++ ) {
					arrayList.add( arr[idx] );
				}
			}
		}
		else {
			ICFBamRelationColObj obj;
			Iterator<ICFBamRelationColObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFBamRelationColObj> cmp = new Comparator<ICFBamRelationColObj>() {
			@Override
			public int compare( ICFBamRelationColObj lhs, ICFBamRelationColObj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		return( arrayList );
	}

	@Override
	public List<ICFBamRelationColObj> readCachedRelationColByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId )
	{
		final String S_ProcName = "readCachedRelationColByDefSchemaIdx";
		ICFBamRelationColByDefSchemaIdxKey key = schema.getCFBamBackingStore().getFactoryRelationCol().newByDefSchemaIdxKey();
		key.setOptionalDefSchemaId( DefSchemaId );
		ArrayList<ICFBamRelationColObj> arrayList = new ArrayList<ICFBamRelationColObj>();
		if( indexByDefSchemaIdx != null ) {
			Map<CFLibDbKeyHash256, ICFBamRelationColObj> dict;
			if( indexByDefSchemaIdx.containsKey( key ) ) {
				dict = indexByDefSchemaIdx.get( key );
				int len = dict.size();
				ICFBamRelationColObj arr[] = new ICFBamRelationColObj[len];
				Iterator<ICFBamRelationColObj> valIter = dict.values().iterator();
				int idx = 0;
				while( ( idx < len ) && valIter.hasNext() ) {
					arr[idx++] = valIter.next();
				}
				if( idx < len ) {
					throw new CFLibArgumentUnderflowException( getClass(),
						S_ProcName,
						0,
						"idx",
						idx,
						len );
				}
				else if( valIter.hasNext() ) {
					throw new CFLibArgumentOverflowException( getClass(),
							S_ProcName,
							0,
							"idx",
							idx,
							len );
				}
				for( idx = 0; idx < len; idx ++ ) {
					arrayList.add( arr[idx] );
				}
			}
		}
		else {
			ICFBamRelationColObj obj;
			Iterator<ICFBamRelationColObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFBamRelationColObj> cmp = new Comparator<ICFBamRelationColObj>() {
			@Override
			public int compare( ICFBamRelationColObj lhs, ICFBamRelationColObj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		return( arrayList );
	}

	@Override
	public List<ICFBamRelationColObj> readCachedRelationColByFromColIdx( CFLibDbKeyHash256 FromColId )
	{
		final String S_ProcName = "readCachedRelationColByFromColIdx";
		ICFBamRelationColByFromColIdxKey key = schema.getCFBamBackingStore().getFactoryRelationCol().newByFromColIdxKey();
		key.setRequiredFromColId( FromColId );
		ArrayList<ICFBamRelationColObj> arrayList = new ArrayList<ICFBamRelationColObj>();
		if( indexByFromColIdx != null ) {
			Map<CFLibDbKeyHash256, ICFBamRelationColObj> dict;
			if( indexByFromColIdx.containsKey( key ) ) {
				dict = indexByFromColIdx.get( key );
				int len = dict.size();
				ICFBamRelationColObj arr[] = new ICFBamRelationColObj[len];
				Iterator<ICFBamRelationColObj> valIter = dict.values().iterator();
				int idx = 0;
				while( ( idx < len ) && valIter.hasNext() ) {
					arr[idx++] = valIter.next();
				}
				if( idx < len ) {
					throw new CFLibArgumentUnderflowException( getClass(),
						S_ProcName,
						0,
						"idx",
						idx,
						len );
				}
				else if( valIter.hasNext() ) {
					throw new CFLibArgumentOverflowException( getClass(),
							S_ProcName,
							0,
							"idx",
							idx,
							len );
				}
				for( idx = 0; idx < len; idx ++ ) {
					arrayList.add( arr[idx] );
				}
			}
		}
		else {
			ICFBamRelationColObj obj;
			Iterator<ICFBamRelationColObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFBamRelationColObj> cmp = new Comparator<ICFBamRelationColObj>() {
			@Override
			public int compare( ICFBamRelationColObj lhs, ICFBamRelationColObj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		return( arrayList );
	}

	@Override
	public List<ICFBamRelationColObj> readCachedRelationColByToColIdx( CFLibDbKeyHash256 ToColId )
	{
		final String S_ProcName = "readCachedRelationColByToColIdx";
		ICFBamRelationColByToColIdxKey key = schema.getCFBamBackingStore().getFactoryRelationCol().newByToColIdxKey();
		key.setRequiredToColId( ToColId );
		ArrayList<ICFBamRelationColObj> arrayList = new ArrayList<ICFBamRelationColObj>();
		if( indexByToColIdx != null ) {
			Map<CFLibDbKeyHash256, ICFBamRelationColObj> dict;
			if( indexByToColIdx.containsKey( key ) ) {
				dict = indexByToColIdx.get( key );
				int len = dict.size();
				ICFBamRelationColObj arr[] = new ICFBamRelationColObj[len];
				Iterator<ICFBamRelationColObj> valIter = dict.values().iterator();
				int idx = 0;
				while( ( idx < len ) && valIter.hasNext() ) {
					arr[idx++] = valIter.next();
				}
				if( idx < len ) {
					throw new CFLibArgumentUnderflowException( getClass(),
						S_ProcName,
						0,
						"idx",
						idx,
						len );
				}
				else if( valIter.hasNext() ) {
					throw new CFLibArgumentOverflowException( getClass(),
							S_ProcName,
							0,
							"idx",
							idx,
							len );
				}
				for( idx = 0; idx < len; idx ++ ) {
					arrayList.add( arr[idx] );
				}
			}
		}
		else {
			ICFBamRelationColObj obj;
			Iterator<ICFBamRelationColObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFBamRelationColObj> cmp = new Comparator<ICFBamRelationColObj>() {
			@Override
			public int compare( ICFBamRelationColObj lhs, ICFBamRelationColObj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		return( arrayList );
	}

	@Override
	public List<ICFBamRelationColObj> readCachedRelationColByPrevIdx( CFLibDbKeyHash256 PrevId )
	{
		final String S_ProcName = "readCachedRelationColByPrevIdx";
		ICFBamRelationColByPrevIdxKey key = schema.getCFBamBackingStore().getFactoryRelationCol().newByPrevIdxKey();
		key.setOptionalPrevId( PrevId );
		ArrayList<ICFBamRelationColObj> arrayList = new ArrayList<ICFBamRelationColObj>();
		if( indexByPrevIdx != null ) {
			Map<CFLibDbKeyHash256, ICFBamRelationColObj> dict;
			if( indexByPrevIdx.containsKey( key ) ) {
				dict = indexByPrevIdx.get( key );
				int len = dict.size();
				ICFBamRelationColObj arr[] = new ICFBamRelationColObj[len];
				Iterator<ICFBamRelationColObj> valIter = dict.values().iterator();
				int idx = 0;
				while( ( idx < len ) && valIter.hasNext() ) {
					arr[idx++] = valIter.next();
				}
				if( idx < len ) {
					throw new CFLibArgumentUnderflowException( getClass(),
						S_ProcName,
						0,
						"idx",
						idx,
						len );
				}
				else if( valIter.hasNext() ) {
					throw new CFLibArgumentOverflowException( getClass(),
							S_ProcName,
							0,
							"idx",
							idx,
							len );
				}
				for( idx = 0; idx < len; idx ++ ) {
					arrayList.add( arr[idx] );
				}
			}
		}
		else {
			ICFBamRelationColObj obj;
			Iterator<ICFBamRelationColObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFBamRelationColObj> cmp = new Comparator<ICFBamRelationColObj>() {
			@Override
			public int compare( ICFBamRelationColObj lhs, ICFBamRelationColObj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		return( arrayList );
	}

	@Override
	public List<ICFBamRelationColObj> readCachedRelationColByNextIdx( CFLibDbKeyHash256 NextId )
	{
		final String S_ProcName = "readCachedRelationColByNextIdx";
		ICFBamRelationColByNextIdxKey key = schema.getCFBamBackingStore().getFactoryRelationCol().newByNextIdxKey();
		key.setOptionalNextId( NextId );
		ArrayList<ICFBamRelationColObj> arrayList = new ArrayList<ICFBamRelationColObj>();
		if( indexByNextIdx != null ) {
			Map<CFLibDbKeyHash256, ICFBamRelationColObj> dict;
			if( indexByNextIdx.containsKey( key ) ) {
				dict = indexByNextIdx.get( key );
				int len = dict.size();
				ICFBamRelationColObj arr[] = new ICFBamRelationColObj[len];
				Iterator<ICFBamRelationColObj> valIter = dict.values().iterator();
				int idx = 0;
				while( ( idx < len ) && valIter.hasNext() ) {
					arr[idx++] = valIter.next();
				}
				if( idx < len ) {
					throw new CFLibArgumentUnderflowException( getClass(),
						S_ProcName,
						0,
						"idx",
						idx,
						len );
				}
				else if( valIter.hasNext() ) {
					throw new CFLibArgumentOverflowException( getClass(),
							S_ProcName,
							0,
							"idx",
							idx,
							len );
				}
				for( idx = 0; idx < len; idx ++ ) {
					arrayList.add( arr[idx] );
				}
			}
		}
		else {
			ICFBamRelationColObj obj;
			Iterator<ICFBamRelationColObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFBamRelationColObj> cmp = new Comparator<ICFBamRelationColObj>() {
			@Override
			public int compare( ICFBamRelationColObj lhs, ICFBamRelationColObj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		return( arrayList );
	}

	@Override
	public List<ICFBamRelationColObj> readCachedRelationColByRelPrevIdx( CFLibDbKeyHash256 RelationId,
		CFLibDbKeyHash256 PrevId )
	{
		final String S_ProcName = "readCachedRelationColByRelPrevIdx";
		ICFBamRelationColByRelPrevIdxKey key = schema.getCFBamBackingStore().getFactoryRelationCol().newByRelPrevIdxKey();
		key.setRequiredRelationId( RelationId );
		key.setOptionalPrevId( PrevId );
		ArrayList<ICFBamRelationColObj> arrayList = new ArrayList<ICFBamRelationColObj>();
		if( indexByRelPrevIdx != null ) {
			Map<CFLibDbKeyHash256, ICFBamRelationColObj> dict;
			if( indexByRelPrevIdx.containsKey( key ) ) {
				dict = indexByRelPrevIdx.get( key );
				int len = dict.size();
				ICFBamRelationColObj arr[] = new ICFBamRelationColObj[len];
				Iterator<ICFBamRelationColObj> valIter = dict.values().iterator();
				int idx = 0;
				while( ( idx < len ) && valIter.hasNext() ) {
					arr[idx++] = valIter.next();
				}
				if( idx < len ) {
					throw new CFLibArgumentUnderflowException( getClass(),
						S_ProcName,
						0,
						"idx",
						idx,
						len );
				}
				else if( valIter.hasNext() ) {
					throw new CFLibArgumentOverflowException( getClass(),
							S_ProcName,
							0,
							"idx",
							idx,
							len );
				}
				for( idx = 0; idx < len; idx ++ ) {
					arrayList.add( arr[idx] );
				}
			}
		}
		else {
			ICFBamRelationColObj obj;
			Iterator<ICFBamRelationColObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFBamRelationColObj> cmp = new Comparator<ICFBamRelationColObj>() {
			@Override
			public int compare( ICFBamRelationColObj lhs, ICFBamRelationColObj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		return( arrayList );
	}

	@Override
	public List<ICFBamRelationColObj> readCachedRelationColByRelNextIdx( CFLibDbKeyHash256 RelationId,
		CFLibDbKeyHash256 NextId )
	{
		final String S_ProcName = "readCachedRelationColByRelNextIdx";
		ICFBamRelationColByRelNextIdxKey key = schema.getCFBamBackingStore().getFactoryRelationCol().newByRelNextIdxKey();
		key.setRequiredRelationId( RelationId );
		key.setOptionalNextId( NextId );
		ArrayList<ICFBamRelationColObj> arrayList = new ArrayList<ICFBamRelationColObj>();
		if( indexByRelNextIdx != null ) {
			Map<CFLibDbKeyHash256, ICFBamRelationColObj> dict;
			if( indexByRelNextIdx.containsKey( key ) ) {
				dict = indexByRelNextIdx.get( key );
				int len = dict.size();
				ICFBamRelationColObj arr[] = new ICFBamRelationColObj[len];
				Iterator<ICFBamRelationColObj> valIter = dict.values().iterator();
				int idx = 0;
				while( ( idx < len ) && valIter.hasNext() ) {
					arr[idx++] = valIter.next();
				}
				if( idx < len ) {
					throw new CFLibArgumentUnderflowException( getClass(),
						S_ProcName,
						0,
						"idx",
						idx,
						len );
				}
				else if( valIter.hasNext() ) {
					throw new CFLibArgumentOverflowException( getClass(),
							S_ProcName,
							0,
							"idx",
							idx,
							len );
				}
				for( idx = 0; idx < len; idx ++ ) {
					arrayList.add( arr[idx] );
				}
			}
		}
		else {
			ICFBamRelationColObj obj;
			Iterator<ICFBamRelationColObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFBamRelationColObj> cmp = new Comparator<ICFBamRelationColObj>() {
			@Override
			public int compare( ICFBamRelationColObj lhs, ICFBamRelationColObj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		return( arrayList );
	}

	@Override
	public void deepDisposeRelationColByIdIdx( CFLibDbKeyHash256 Id )
	{
		ICFBamRelationColObj obj = readCachedRelationColByIdIdx( Id );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public void deepDisposeRelationColByUNameIdx( CFLibDbKeyHash256 RelationId,
		String Name )
	{
		ICFBamRelationColObj obj = readCachedRelationColByUNameIdx( RelationId,
				Name );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public void deepDisposeRelationColByRelationIdx( CFLibDbKeyHash256 RelationId )
	{
		final String S_ProcName = "deepDisposeRelationColByRelationIdx";
		ICFBamRelationColObj obj;
		List<ICFBamRelationColObj> arrayList = readCachedRelationColByRelationIdx( RelationId );
		if( arrayList != null )  {
			Iterator<ICFBamRelationColObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public void deepDisposeRelationColByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId )
	{
		final String S_ProcName = "deepDisposeRelationColByDefSchemaIdx";
		ICFBamRelationColObj obj;
		List<ICFBamRelationColObj> arrayList = readCachedRelationColByDefSchemaIdx( DefSchemaId );
		if( arrayList != null )  {
			Iterator<ICFBamRelationColObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public void deepDisposeRelationColByFromColIdx( CFLibDbKeyHash256 FromColId )
	{
		final String S_ProcName = "deepDisposeRelationColByFromColIdx";
		ICFBamRelationColObj obj;
		List<ICFBamRelationColObj> arrayList = readCachedRelationColByFromColIdx( FromColId );
		if( arrayList != null )  {
			Iterator<ICFBamRelationColObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public void deepDisposeRelationColByToColIdx( CFLibDbKeyHash256 ToColId )
	{
		final String S_ProcName = "deepDisposeRelationColByToColIdx";
		ICFBamRelationColObj obj;
		List<ICFBamRelationColObj> arrayList = readCachedRelationColByToColIdx( ToColId );
		if( arrayList != null )  {
			Iterator<ICFBamRelationColObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public void deepDisposeRelationColByPrevIdx( CFLibDbKeyHash256 PrevId )
	{
		final String S_ProcName = "deepDisposeRelationColByPrevIdx";
		ICFBamRelationColObj obj;
		List<ICFBamRelationColObj> arrayList = readCachedRelationColByPrevIdx( PrevId );
		if( arrayList != null )  {
			Iterator<ICFBamRelationColObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public void deepDisposeRelationColByNextIdx( CFLibDbKeyHash256 NextId )
	{
		final String S_ProcName = "deepDisposeRelationColByNextIdx";
		ICFBamRelationColObj obj;
		List<ICFBamRelationColObj> arrayList = readCachedRelationColByNextIdx( NextId );
		if( arrayList != null )  {
			Iterator<ICFBamRelationColObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public void deepDisposeRelationColByRelPrevIdx( CFLibDbKeyHash256 RelationId,
		CFLibDbKeyHash256 PrevId )
	{
		final String S_ProcName = "deepDisposeRelationColByRelPrevIdx";
		ICFBamRelationColObj obj;
		List<ICFBamRelationColObj> arrayList = readCachedRelationColByRelPrevIdx( RelationId,
				PrevId );
		if( arrayList != null )  {
			Iterator<ICFBamRelationColObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public void deepDisposeRelationColByRelNextIdx( CFLibDbKeyHash256 RelationId,
		CFLibDbKeyHash256 NextId )
	{
		final String S_ProcName = "deepDisposeRelationColByRelNextIdx";
		ICFBamRelationColObj obj;
		List<ICFBamRelationColObj> arrayList = readCachedRelationColByRelNextIdx( RelationId,
				NextId );
		if( arrayList != null )  {
			Iterator<ICFBamRelationColObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public ICFBamRelationColObj updateRelationCol( ICFBamRelationColObj Obj ) {
		ICFBamRelationColObj obj = Obj;
		schema.getCFBamBackingStore().getTableRelationCol().updateRelationCol( null,
			Obj.getRelationColRec() );
		obj = (ICFBamRelationColObj)Obj.realise();
		obj.endEdit();
		return( obj );
	}

	@Override
	public void deleteRelationCol( ICFBamRelationColObj Obj ) {
		ICFBamRelationColObj obj = Obj;
		ICFBamRelationColObj prev = obj.getOptionalLookupPrev();
		ICFBamRelationColObj next = obj.getOptionalLookupNext();
		schema.getCFBamBackingStore().getTableRelationCol().deleteRelationCol( null,
			obj.getRelationColRec() );
		Obj.forget();
		if( prev != null ) {
			prev.read( true );
		}
		if( next != null ) {
			next.read( true );
		}
	}

	@Override
	public void deleteRelationColByIdIdx( CFLibDbKeyHash256 Id )
	{
		ICFBamRelationColObj obj = readRelationCol(Id);
		if( obj != null ) {
			ICFBamRelationColEditObj editObj = (ICFBamRelationColEditObj)obj.getEdit();
			boolean editStarted;
			if( editObj == null ) {
				editObj = (ICFBamRelationColEditObj)obj.beginEdit();
				if( editObj != null ) {
					editStarted = true;
				}
				else {
					editStarted = false;
				}
			}
			else {
				editStarted = false;
			}
			if( editObj != null ) {
				editObj.deleteInstance();
				if( editStarted ) {
					editObj.endEdit();
				}
			}
			obj.forget();
		}
		deepDisposeRelationColByIdIdx( Id );
	}

	@Override
	public void deleteRelationColByUNameIdx( CFLibDbKeyHash256 RelationId,
		String Name )
	{
		if( indexByUNameIdx == null ) {
			indexByUNameIdx = new HashMap< ICFBamRelationColByUNameIdxKey,
				ICFBamRelationColObj >();
		}
		ICFBamRelationColByUNameIdxKey key = schema.getCFBamBackingStore().getFactoryRelationCol().newByUNameIdxKey();
		key.setRequiredRelationId( RelationId );
		key.setRequiredName( Name );
		ICFBamRelationColObj obj = null;
		if( indexByUNameIdx.containsKey( key ) ) {
			obj = indexByUNameIdx.get( key );
			schema.getCFBamBackingStore().getTableRelationCol().deleteRelationColByUNameIdx( null,
				RelationId,
				Name );
			obj.forget();
		}
		else {
			schema.getCFBamBackingStore().getTableRelationCol().deleteRelationColByUNameIdx( null,
				RelationId,
				Name );
		}
		deepDisposeRelationColByUNameIdx( RelationId,
				Name );
	}

	@Override
	public void deleteRelationColByRelationIdx( CFLibDbKeyHash256 RelationId )
	{
		ICFBamRelationColByRelationIdxKey key = schema.getCFBamBackingStore().getFactoryRelationCol().newByRelationIdxKey();
		key.setRequiredRelationId( RelationId );
		if( indexByRelationIdx == null ) {
			indexByRelationIdx = new HashMap< ICFBamRelationColByRelationIdxKey,
				Map< CFLibDbKeyHash256, ICFBamRelationColObj > >();
		}
		if( indexByRelationIdx.containsKey( key ) ) {
			Map<CFLibDbKeyHash256, ICFBamRelationColObj> dict = indexByRelationIdx.get( key );
			schema.getCFBamBackingStore().getTableRelationCol().deleteRelationColByRelationIdx( null,
				RelationId );
			Iterator<ICFBamRelationColObj> iter = dict.values().iterator();
			ICFBamRelationColObj obj;
			List<ICFBamRelationColObj> toForget = new LinkedList<ICFBamRelationColObj>();
			while( iter.hasNext() ) {
				obj = iter.next();
				toForget.add( obj );
			}
			iter = toForget.iterator();
			while( iter.hasNext() ) {
				obj = iter.next();
				obj.forget();
			}
			indexByRelationIdx.remove( key );
		}
		else {
			schema.getCFBamBackingStore().getTableRelationCol().deleteRelationColByRelationIdx( null,
				RelationId );
		}
		deepDisposeRelationColByRelationIdx( RelationId );
	}

	@Override
	public void deleteRelationColByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId )
	{
		ICFBamRelationColByDefSchemaIdxKey key = schema.getCFBamBackingStore().getFactoryRelationCol().newByDefSchemaIdxKey();
		key.setOptionalDefSchemaId( DefSchemaId );
		if( indexByDefSchemaIdx == null ) {
			indexByDefSchemaIdx = new HashMap< ICFBamRelationColByDefSchemaIdxKey,
				Map< CFLibDbKeyHash256, ICFBamRelationColObj > >();
		}
		if( indexByDefSchemaIdx.containsKey( key ) ) {
			Map<CFLibDbKeyHash256, ICFBamRelationColObj> dict = indexByDefSchemaIdx.get( key );
			schema.getCFBamBackingStore().getTableRelationCol().deleteRelationColByDefSchemaIdx( null,
				DefSchemaId );
			Iterator<ICFBamRelationColObj> iter = dict.values().iterator();
			ICFBamRelationColObj obj;
			List<ICFBamRelationColObj> toForget = new LinkedList<ICFBamRelationColObj>();
			while( iter.hasNext() ) {
				obj = iter.next();
				toForget.add( obj );
			}
			iter = toForget.iterator();
			while( iter.hasNext() ) {
				obj = iter.next();
				obj.forget();
			}
			indexByDefSchemaIdx.remove( key );
		}
		else {
			schema.getCFBamBackingStore().getTableRelationCol().deleteRelationColByDefSchemaIdx( null,
				DefSchemaId );
		}
		deepDisposeRelationColByDefSchemaIdx( DefSchemaId );
	}

	@Override
	public void deleteRelationColByFromColIdx( CFLibDbKeyHash256 FromColId )
	{
		ICFBamRelationColByFromColIdxKey key = schema.getCFBamBackingStore().getFactoryRelationCol().newByFromColIdxKey();
		key.setRequiredFromColId( FromColId );
		if( indexByFromColIdx == null ) {
			indexByFromColIdx = new HashMap< ICFBamRelationColByFromColIdxKey,
				Map< CFLibDbKeyHash256, ICFBamRelationColObj > >();
		}
		if( indexByFromColIdx.containsKey( key ) ) {
			Map<CFLibDbKeyHash256, ICFBamRelationColObj> dict = indexByFromColIdx.get( key );
			schema.getCFBamBackingStore().getTableRelationCol().deleteRelationColByFromColIdx( null,
				FromColId );
			Iterator<ICFBamRelationColObj> iter = dict.values().iterator();
			ICFBamRelationColObj obj;
			List<ICFBamRelationColObj> toForget = new LinkedList<ICFBamRelationColObj>();
			while( iter.hasNext() ) {
				obj = iter.next();
				toForget.add( obj );
			}
			iter = toForget.iterator();
			while( iter.hasNext() ) {
				obj = iter.next();
				obj.forget();
			}
			indexByFromColIdx.remove( key );
		}
		else {
			schema.getCFBamBackingStore().getTableRelationCol().deleteRelationColByFromColIdx( null,
				FromColId );
		}
		deepDisposeRelationColByFromColIdx( FromColId );
	}

	@Override
	public void deleteRelationColByToColIdx( CFLibDbKeyHash256 ToColId )
	{
		ICFBamRelationColByToColIdxKey key = schema.getCFBamBackingStore().getFactoryRelationCol().newByToColIdxKey();
		key.setRequiredToColId( ToColId );
		if( indexByToColIdx == null ) {
			indexByToColIdx = new HashMap< ICFBamRelationColByToColIdxKey,
				Map< CFLibDbKeyHash256, ICFBamRelationColObj > >();
		}
		if( indexByToColIdx.containsKey( key ) ) {
			Map<CFLibDbKeyHash256, ICFBamRelationColObj> dict = indexByToColIdx.get( key );
			schema.getCFBamBackingStore().getTableRelationCol().deleteRelationColByToColIdx( null,
				ToColId );
			Iterator<ICFBamRelationColObj> iter = dict.values().iterator();
			ICFBamRelationColObj obj;
			List<ICFBamRelationColObj> toForget = new LinkedList<ICFBamRelationColObj>();
			while( iter.hasNext() ) {
				obj = iter.next();
				toForget.add( obj );
			}
			iter = toForget.iterator();
			while( iter.hasNext() ) {
				obj = iter.next();
				obj.forget();
			}
			indexByToColIdx.remove( key );
		}
		else {
			schema.getCFBamBackingStore().getTableRelationCol().deleteRelationColByToColIdx( null,
				ToColId );
		}
		deepDisposeRelationColByToColIdx( ToColId );
	}

	@Override
	public void deleteRelationColByPrevIdx( CFLibDbKeyHash256 PrevId )
	{
		ICFBamRelationColByPrevIdxKey key = schema.getCFBamBackingStore().getFactoryRelationCol().newByPrevIdxKey();
		key.setOptionalPrevId( PrevId );
		if( indexByPrevIdx == null ) {
			indexByPrevIdx = new HashMap< ICFBamRelationColByPrevIdxKey,
				Map< CFLibDbKeyHash256, ICFBamRelationColObj > >();
		}
		if( indexByPrevIdx.containsKey( key ) ) {
			Map<CFLibDbKeyHash256, ICFBamRelationColObj> dict = indexByPrevIdx.get( key );
			schema.getCFBamBackingStore().getTableRelationCol().deleteRelationColByPrevIdx( null,
				PrevId );
			Iterator<ICFBamRelationColObj> iter = dict.values().iterator();
			ICFBamRelationColObj obj;
			List<ICFBamRelationColObj> toForget = new LinkedList<ICFBamRelationColObj>();
			while( iter.hasNext() ) {
				obj = iter.next();
				toForget.add( obj );
			}
			iter = toForget.iterator();
			while( iter.hasNext() ) {
				obj = iter.next();
				obj.forget();
			}
			indexByPrevIdx.remove( key );
		}
		else {
			schema.getCFBamBackingStore().getTableRelationCol().deleteRelationColByPrevIdx( null,
				PrevId );
		}
		deepDisposeRelationColByPrevIdx( PrevId );
	}

	@Override
	public void deleteRelationColByNextIdx( CFLibDbKeyHash256 NextId )
	{
		ICFBamRelationColByNextIdxKey key = schema.getCFBamBackingStore().getFactoryRelationCol().newByNextIdxKey();
		key.setOptionalNextId( NextId );
		if( indexByNextIdx == null ) {
			indexByNextIdx = new HashMap< ICFBamRelationColByNextIdxKey,
				Map< CFLibDbKeyHash256, ICFBamRelationColObj > >();
		}
		if( indexByNextIdx.containsKey( key ) ) {
			Map<CFLibDbKeyHash256, ICFBamRelationColObj> dict = indexByNextIdx.get( key );
			schema.getCFBamBackingStore().getTableRelationCol().deleteRelationColByNextIdx( null,
				NextId );
			Iterator<ICFBamRelationColObj> iter = dict.values().iterator();
			ICFBamRelationColObj obj;
			List<ICFBamRelationColObj> toForget = new LinkedList<ICFBamRelationColObj>();
			while( iter.hasNext() ) {
				obj = iter.next();
				toForget.add( obj );
			}
			iter = toForget.iterator();
			while( iter.hasNext() ) {
				obj = iter.next();
				obj.forget();
			}
			indexByNextIdx.remove( key );
		}
		else {
			schema.getCFBamBackingStore().getTableRelationCol().deleteRelationColByNextIdx( null,
				NextId );
		}
		deepDisposeRelationColByNextIdx( NextId );
	}

	@Override
	public void deleteRelationColByRelPrevIdx( CFLibDbKeyHash256 RelationId,
		CFLibDbKeyHash256 PrevId )
	{
		ICFBamRelationColByRelPrevIdxKey key = schema.getCFBamBackingStore().getFactoryRelationCol().newByRelPrevIdxKey();
		key.setRequiredRelationId( RelationId );
		key.setOptionalPrevId( PrevId );
		if( indexByRelPrevIdx == null ) {
			indexByRelPrevIdx = new HashMap< ICFBamRelationColByRelPrevIdxKey,
				Map< CFLibDbKeyHash256, ICFBamRelationColObj > >();
		}
		if( indexByRelPrevIdx.containsKey( key ) ) {
			Map<CFLibDbKeyHash256, ICFBamRelationColObj> dict = indexByRelPrevIdx.get( key );
			schema.getCFBamBackingStore().getTableRelationCol().deleteRelationColByRelPrevIdx( null,
				RelationId,
				PrevId );
			Iterator<ICFBamRelationColObj> iter = dict.values().iterator();
			ICFBamRelationColObj obj;
			List<ICFBamRelationColObj> toForget = new LinkedList<ICFBamRelationColObj>();
			while( iter.hasNext() ) {
				obj = iter.next();
				toForget.add( obj );
			}
			iter = toForget.iterator();
			while( iter.hasNext() ) {
				obj = iter.next();
				obj.forget();
			}
			indexByRelPrevIdx.remove( key );
		}
		else {
			schema.getCFBamBackingStore().getTableRelationCol().deleteRelationColByRelPrevIdx( null,
				RelationId,
				PrevId );
		}
		deepDisposeRelationColByRelPrevIdx( RelationId,
				PrevId );
	}

	@Override
	public void deleteRelationColByRelNextIdx( CFLibDbKeyHash256 RelationId,
		CFLibDbKeyHash256 NextId )
	{
		ICFBamRelationColByRelNextIdxKey key = schema.getCFBamBackingStore().getFactoryRelationCol().newByRelNextIdxKey();
		key.setRequiredRelationId( RelationId );
		key.setOptionalNextId( NextId );
		if( indexByRelNextIdx == null ) {
			indexByRelNextIdx = new HashMap< ICFBamRelationColByRelNextIdxKey,
				Map< CFLibDbKeyHash256, ICFBamRelationColObj > >();
		}
		if( indexByRelNextIdx.containsKey( key ) ) {
			Map<CFLibDbKeyHash256, ICFBamRelationColObj> dict = indexByRelNextIdx.get( key );
			schema.getCFBamBackingStore().getTableRelationCol().deleteRelationColByRelNextIdx( null,
				RelationId,
				NextId );
			Iterator<ICFBamRelationColObj> iter = dict.values().iterator();
			ICFBamRelationColObj obj;
			List<ICFBamRelationColObj> toForget = new LinkedList<ICFBamRelationColObj>();
			while( iter.hasNext() ) {
				obj = iter.next();
				toForget.add( obj );
			}
			iter = toForget.iterator();
			while( iter.hasNext() ) {
				obj = iter.next();
				obj.forget();
			}
			indexByRelNextIdx.remove( key );
		}
		else {
			schema.getCFBamBackingStore().getTableRelationCol().deleteRelationColByRelNextIdx( null,
				RelationId,
				NextId );
		}
		deepDisposeRelationColByRelNextIdx( RelationId,
				NextId );
	}

	/**
	 *	Move the CFBamRelationColObj instance up in the chain.  The instance is always refreshed.
	 *
	 *	@return	CFBamRelationColObj refreshed cache instance.
	 */
	@Override
	public ICFBamRelationColObj moveUpRelationCol( ICFBamRelationColObj Obj ) {
		ICFBamRelationColObj obj = null;
		if( null != Obj.getEdit() ) {
			throw new CFLibCannotMoveEditedObjectException( getClass(),	"moveUpRelationCol" );
		}
		ICFBamRelationCol rec = schema.getCFBamBackingStore().getTableRelationCol().moveRecUp( null,
			Obj.getRequiredId(),
			Obj.getRec().getRequiredRevision() );
		if( rec != null ) {
			obj = schema.getRelationColTableObj().newInstance();
			obj.setPKey( rec.getPKey() );
			obj.setRec( rec );
			obj = (ICFBamRelationColObj)obj.realise();
			ICFBamRelationColObj prev = obj.getOptionalLookupPrev( true );
			ICFBamRelationColObj next = obj.getOptionalLookupNext( true );
			if( next != null ) {
				ICFBamRelationColObj gnext = next.getOptionalLookupNext( true );
			}
		}
		return( obj );
	}

	/**
	 *	Move the CFBamRelationColObj instance down in the chain.  The instance is always refreshed.
	 *
	 *	@return	CFBamRelationColObj refreshed cache instance.
	 */
	@Override
	public ICFBamRelationColObj moveDownRelationCol( ICFBamRelationColObj Obj ) {
		ICFBamRelationColObj obj = null;
		if( null != Obj.getEdit() ) {
			throw new CFLibCannotMoveEditedObjectException( getClass(),	"moveDownRelationCol" );
		}
		ICFBamRelationCol rec = schema.getCFBamBackingStore().getTableRelationCol().moveRecDown( null,
			Obj.getRequiredId(),
			Obj.getRec().getRequiredRevision() );
		if( rec != null ) {
			obj = schema.getRelationColTableObj().newInstance();
			obj.setPKey( rec.getPKey() );
			obj.setRec( rec );
			obj = (ICFBamRelationColObj)obj.realise();
			ICFBamRelationColObj prev = obj.getOptionalLookupPrev( true );
			if( prev != null ) {
				ICFBamRelationColObj gprev = prev.getOptionalLookupPrev( true );
			}
			ICFBamRelationColObj next = obj.getOptionalLookupNext( true );
		}
		return( obj );
	}
}