// Description: Java 25 Table Object implementation for DelTopDep.

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

public class CFBamDelTopDepTableObj
	implements ICFBamDelTopDepTableObj
{
	protected ICFBamSchemaObj schema;
	protected static int runtimeClassCode = ICFBamDelTopDep.CLASS_CODE;
	protected static final int backingClassCode = ICFBamDelTopDep.CLASS_CODE;
	private Map<CFLibDbKeyHash256, ICFBamDelTopDepObj> members;
	private Map<CFLibDbKeyHash256, ICFBamDelTopDepObj> allDelTopDep;
	private Map< ICFBamScopeByTenantIdxKey,
		Map<CFLibDbKeyHash256, ICFBamDelTopDepObj > > indexByTenantIdx;
	private Map< ICFBamDelDepByDefSchemaIdxKey,
		Map<CFLibDbKeyHash256, ICFBamDelTopDepObj > > indexByDefSchemaIdx;
	private Map< ICFBamDelDepByDelDepIdxKey,
		Map<CFLibDbKeyHash256, ICFBamDelTopDepObj > > indexByDelDepIdx;
	private Map< ICFBamDelTopDepByDelTopDepTblIdxKey,
		Map<CFLibDbKeyHash256, ICFBamDelTopDepObj > > indexByDelTopDepTblIdx;
	private Map< ICFBamDelTopDepByUNameIdxKey,
		ICFBamDelTopDepObj > indexByUNameIdx;
	private Map< ICFBamDelTopDepByPrevIdxKey,
		Map<CFLibDbKeyHash256, ICFBamDelTopDepObj > > indexByPrevIdx;
	private Map< ICFBamDelTopDepByNextIdxKey,
		Map<CFLibDbKeyHash256, ICFBamDelTopDepObj > > indexByNextIdx;
	public static String TABLE_NAME = "DelTopDep";
	public static String TABLE_DBNAME = "del_topdep";

	public CFBamDelTopDepTableObj() {
		schema = null;
		members = new HashMap<CFLibDbKeyHash256, ICFBamDelTopDepObj>();
		allDelTopDep = null;
		indexByTenantIdx = null;
		indexByDefSchemaIdx = null;
		indexByDelDepIdx = null;
		indexByDelTopDepTblIdx = null;
		indexByUNameIdx = null;
		indexByPrevIdx = null;
		indexByNextIdx = null;
	}

	public CFBamDelTopDepTableObj( ICFBamSchemaObj argSchema ) {
		schema = (ICFBamSchemaObj)argSchema;
		members = new HashMap<CFLibDbKeyHash256, ICFBamDelTopDepObj>();
		allDelTopDep = null;
		indexByTenantIdx = null;
		indexByDefSchemaIdx = null;
		indexByDelDepIdx = null;
		indexByDelTopDepTblIdx = null;
		indexByUNameIdx = null;
		indexByPrevIdx = null;
		indexByNextIdx = null;
	}
	
	/**
	 *	Get class code always returns the runtime class code for the objects, which is not stable until the application is done initializing and registering its objects.
	 *
	 *	@return runtime classcode
	 */ 
	@Override
	public int getClassCode() {
		return CFBamDelTopDepTableObj.getRuntimeClassCode();
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
			throw new CFLibArgumentUnderflowException(CFBamDelTopDepTableObj.class, "setRuntimeClassCode", 1, "argNewClassCode", argNewClassCode, 1);
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
		allDelTopDep = null;
		indexByTenantIdx = null;
		indexByDefSchemaIdx = null;
		indexByDelDepIdx = null;
		indexByDelTopDepTblIdx = null;
		indexByUNameIdx = null;
		indexByPrevIdx = null;
		indexByNextIdx = null;
		List<ICFBamDelTopDepObj> toForget = new LinkedList<ICFBamDelTopDepObj>();
		ICFBamDelTopDepObj cur = null;
		Iterator<ICFBamDelTopDepObj> iter = members.values().iterator();
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
	 *	CFBamDelTopDepObj.
	 */
	@Override
	public ICFBamDelTopDepObj newInstance() {
		ICFBamDelTopDepObj inst = new CFBamDelTopDepObj( schema );
		return( inst );
	}

	/**
	 *	If your implementation subclasses the objects,
	 *	you'll want to overload the constructByClassCode()
	 *	implementation to return your implementation's
	 *	instances instead of the base implementation.
	 *
	 *	This is the sole factory for instances derived from
	 *	CFBamDelTopDepObj.
	 */
	@Override
	public ICFBamDelTopDepEditObj newEditInstance( ICFBamDelTopDepObj orig ) {
		ICFBamDelTopDepEditObj edit = new CFBamDelTopDepEditObj( orig );
		return( edit );
	}

	@Override
	public ICFBamDelTopDepObj realiseDelTopDep( ICFBamDelTopDepObj Obj ) {
		ICFBamDelTopDepObj obj = Obj;
		CFLibDbKeyHash256 pkey = obj.getPKey();
		ICFBamDelTopDepObj keepObj = null;
		if( members.containsKey( pkey ) && ( null != members.get( pkey ) ) ) {
			ICFBamDelTopDepObj existingObj = members.get( pkey );
			keepObj = existingObj;

			/*
			 *	We always rebind the data because if we're being called, some index has
			 *	been updated and is refreshing it's data, which may or may not have changed
			 */

			// Detach object from alternate and duplicate indexes, leave PKey alone

			if( indexByTenantIdx != null ) {
				ICFBamScopeByTenantIdxKey keyTenantIdx =
					schema.getCFBamBackingStore().getFactoryScope().newByTenantIdxKey();
				keyTenantIdx.setRequiredTenantId( keepObj.getRequiredTenantId() );
				Map<CFLibDbKeyHash256, ICFBamDelTopDepObj > mapTenantIdx = indexByTenantIdx.get( keyTenantIdx );
				if( mapTenantIdx != null ) {
					indexByTenantIdx.remove( keyTenantIdx );
				}
			}

			if( indexByDefSchemaIdx != null ) {
				ICFBamDelDepByDefSchemaIdxKey keyDefSchemaIdx =
					schema.getCFBamBackingStore().getFactoryDelDep().newByDefSchemaIdxKey();
				keyDefSchemaIdx.setOptionalDefSchemaId( keepObj.getOptionalDefSchemaId() );
				Map<CFLibDbKeyHash256, ICFBamDelTopDepObj > mapDefSchemaIdx = indexByDefSchemaIdx.get( keyDefSchemaIdx );
				if( mapDefSchemaIdx != null ) {
					indexByDefSchemaIdx.remove( keyDefSchemaIdx );
				}
			}

			if( indexByDelDepIdx != null ) {
				ICFBamDelDepByDelDepIdxKey keyDelDepIdx =
					schema.getCFBamBackingStore().getFactoryDelDep().newByDelDepIdxKey();
				keyDelDepIdx.setRequiredRelationId( keepObj.getRequiredRelationId() );
				Map<CFLibDbKeyHash256, ICFBamDelTopDepObj > mapDelDepIdx = indexByDelDepIdx.get( keyDelDepIdx );
				if( mapDelDepIdx != null ) {
					indexByDelDepIdx.remove( keyDelDepIdx );
				}
			}

			if( indexByDelTopDepTblIdx != null ) {
				ICFBamDelTopDepByDelTopDepTblIdxKey keyDelTopDepTblIdx =
					schema.getCFBamBackingStore().getFactoryDelTopDep().newByDelTopDepTblIdxKey();
				keyDelTopDepTblIdx.setRequiredTableId( keepObj.getRequiredTableId() );
				Map<CFLibDbKeyHash256, ICFBamDelTopDepObj > mapDelTopDepTblIdx = indexByDelTopDepTblIdx.get( keyDelTopDepTblIdx );
				if( mapDelTopDepTblIdx != null ) {
					mapDelTopDepTblIdx.remove( keepObj.getPKey() );
					if( mapDelTopDepTblIdx.size() <= 0 ) {
						indexByDelTopDepTblIdx.remove( keyDelTopDepTblIdx );
					}
				}
			}

			if( indexByUNameIdx != null ) {
				ICFBamDelTopDepByUNameIdxKey keyUNameIdx =
					schema.getCFBamBackingStore().getFactoryDelTopDep().newByUNameIdxKey();
				keyUNameIdx.setRequiredTableId( keepObj.getRequiredTableId() );
				keyUNameIdx.setRequiredName( keepObj.getRequiredName() );
				indexByUNameIdx.remove( keyUNameIdx );
			}

			if( indexByPrevIdx != null ) {
				ICFBamDelTopDepByPrevIdxKey keyPrevIdx =
					schema.getCFBamBackingStore().getFactoryDelTopDep().newByPrevIdxKey();
				keyPrevIdx.setOptionalPrevId( keepObj.getOptionalPrevId() );
				Map<CFLibDbKeyHash256, ICFBamDelTopDepObj > mapPrevIdx = indexByPrevIdx.get( keyPrevIdx );
				if( mapPrevIdx != null ) {
					mapPrevIdx.remove( keepObj.getPKey() );
					if( mapPrevIdx.size() <= 0 ) {
						indexByPrevIdx.remove( keyPrevIdx );
					}
				}
			}

			if( indexByNextIdx != null ) {
				ICFBamDelTopDepByNextIdxKey keyNextIdx =
					schema.getCFBamBackingStore().getFactoryDelTopDep().newByNextIdxKey();
				keyNextIdx.setOptionalNextId( keepObj.getOptionalNextId() );
				Map<CFLibDbKeyHash256, ICFBamDelTopDepObj > mapNextIdx = indexByNextIdx.get( keyNextIdx );
				if( mapNextIdx != null ) {
					mapNextIdx.remove( keepObj.getPKey() );
					if( mapNextIdx.size() <= 0 ) {
						indexByNextIdx.remove( keyNextIdx );
					}
				}
			}
			// Keep passing the new object because it's the one with the record
			// that the base table needs to copy to the existing object from
			// the cache.
			keepObj = (ICFBamDelTopDepObj)schema.getDelDepTableObj().realiseDelDep( Obj );

			// Attach new object to alternate and duplicate indexes -- PKey stay stable

			if( indexByTenantIdx != null ) {
				ICFBamScopeByTenantIdxKey keyTenantIdx =
					schema.getCFBamBackingStore().getFactoryScope().newByTenantIdxKey();
				keyTenantIdx.setRequiredTenantId( keepObj.getRequiredTenantId() );
				Map<CFLibDbKeyHash256, ICFBamDelTopDepObj > mapTenantIdx = indexByTenantIdx.get( keyTenantIdx );
				if( mapTenantIdx != null ) {
					mapTenantIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByDefSchemaIdx != null ) {
				ICFBamDelDepByDefSchemaIdxKey keyDefSchemaIdx =
					schema.getCFBamBackingStore().getFactoryDelDep().newByDefSchemaIdxKey();
				keyDefSchemaIdx.setOptionalDefSchemaId( keepObj.getOptionalDefSchemaId() );
				Map<CFLibDbKeyHash256, ICFBamDelTopDepObj > mapDefSchemaIdx = indexByDefSchemaIdx.get( keyDefSchemaIdx );
				if( mapDefSchemaIdx != null ) {
					mapDefSchemaIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByDelDepIdx != null ) {
				ICFBamDelDepByDelDepIdxKey keyDelDepIdx =
					schema.getCFBamBackingStore().getFactoryDelDep().newByDelDepIdxKey();
				keyDelDepIdx.setRequiredRelationId( keepObj.getRequiredRelationId() );
				Map<CFLibDbKeyHash256, ICFBamDelTopDepObj > mapDelDepIdx = indexByDelDepIdx.get( keyDelDepIdx );
				if( mapDelDepIdx != null ) {
					mapDelDepIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByDelTopDepTblIdx != null ) {
				ICFBamDelTopDepByDelTopDepTblIdxKey keyDelTopDepTblIdx =
					schema.getCFBamBackingStore().getFactoryDelTopDep().newByDelTopDepTblIdxKey();
				keyDelTopDepTblIdx.setRequiredTableId( keepObj.getRequiredTableId() );
				Map<CFLibDbKeyHash256, ICFBamDelTopDepObj > mapDelTopDepTblIdx = indexByDelTopDepTblIdx.get( keyDelTopDepTblIdx );
				if( mapDelTopDepTblIdx != null ) {
					mapDelTopDepTblIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByUNameIdx != null ) {
				ICFBamDelTopDepByUNameIdxKey keyUNameIdx =
					schema.getCFBamBackingStore().getFactoryDelTopDep().newByUNameIdxKey();
				keyUNameIdx.setRequiredTableId( keepObj.getRequiredTableId() );
				keyUNameIdx.setRequiredName( keepObj.getRequiredName() );
				indexByUNameIdx.put( keyUNameIdx, keepObj );
			}

			if( indexByPrevIdx != null ) {
				ICFBamDelTopDepByPrevIdxKey keyPrevIdx =
					schema.getCFBamBackingStore().getFactoryDelTopDep().newByPrevIdxKey();
				keyPrevIdx.setOptionalPrevId( keepObj.getOptionalPrevId() );
				Map<CFLibDbKeyHash256, ICFBamDelTopDepObj > mapPrevIdx = indexByPrevIdx.get( keyPrevIdx );
				if( mapPrevIdx != null ) {
					mapPrevIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByNextIdx != null ) {
				ICFBamDelTopDepByNextIdxKey keyNextIdx =
					schema.getCFBamBackingStore().getFactoryDelTopDep().newByNextIdxKey();
				keyNextIdx.setOptionalNextId( keepObj.getOptionalNextId() );
				Map<CFLibDbKeyHash256, ICFBamDelTopDepObj > mapNextIdx = indexByNextIdx.get( keyNextIdx );
				if( mapNextIdx != null ) {
					mapNextIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( allDelTopDep != null ) {
				allDelTopDep.put( keepObj.getPKey(), keepObj );
			}
		}
		else {
			keepObj = obj;
			keepObj = (ICFBamDelTopDepObj)schema.getDelDepTableObj().realiseDelDep( keepObj );

			// Attach new object to PKey, all, alternate, and duplicate indexes
			members.put( keepObj.getPKey(), keepObj );
			if( allDelTopDep != null ) {
				allDelTopDep.put( keepObj.getPKey(), keepObj );
			}

			if( indexByTenantIdx != null ) {
				ICFBamScopeByTenantIdxKey keyTenantIdx =
					schema.getCFBamBackingStore().getFactoryScope().newByTenantIdxKey();
				keyTenantIdx.setRequiredTenantId( keepObj.getRequiredTenantId() );
				Map<CFLibDbKeyHash256, ICFBamDelTopDepObj > mapTenantIdx = indexByTenantIdx.get( keyTenantIdx );
				if( mapTenantIdx != null ) {
					mapTenantIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByDefSchemaIdx != null ) {
				ICFBamDelDepByDefSchemaIdxKey keyDefSchemaIdx =
					schema.getCFBamBackingStore().getFactoryDelDep().newByDefSchemaIdxKey();
				keyDefSchemaIdx.setOptionalDefSchemaId( keepObj.getOptionalDefSchemaId() );
				Map<CFLibDbKeyHash256, ICFBamDelTopDepObj > mapDefSchemaIdx = indexByDefSchemaIdx.get( keyDefSchemaIdx );
				if( mapDefSchemaIdx != null ) {
					mapDefSchemaIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByDelDepIdx != null ) {
				ICFBamDelDepByDelDepIdxKey keyDelDepIdx =
					schema.getCFBamBackingStore().getFactoryDelDep().newByDelDepIdxKey();
				keyDelDepIdx.setRequiredRelationId( keepObj.getRequiredRelationId() );
				Map<CFLibDbKeyHash256, ICFBamDelTopDepObj > mapDelDepIdx = indexByDelDepIdx.get( keyDelDepIdx );
				if( mapDelDepIdx != null ) {
					mapDelDepIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByDelTopDepTblIdx != null ) {
				ICFBamDelTopDepByDelTopDepTblIdxKey keyDelTopDepTblIdx =
					schema.getCFBamBackingStore().getFactoryDelTopDep().newByDelTopDepTblIdxKey();
				keyDelTopDepTblIdx.setRequiredTableId( keepObj.getRequiredTableId() );
				Map<CFLibDbKeyHash256, ICFBamDelTopDepObj > mapDelTopDepTblIdx = indexByDelTopDepTblIdx.get( keyDelTopDepTblIdx );
				if( mapDelTopDepTblIdx != null ) {
					mapDelTopDepTblIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByUNameIdx != null ) {
				ICFBamDelTopDepByUNameIdxKey keyUNameIdx =
					schema.getCFBamBackingStore().getFactoryDelTopDep().newByUNameIdxKey();
				keyUNameIdx.setRequiredTableId( keepObj.getRequiredTableId() );
				keyUNameIdx.setRequiredName( keepObj.getRequiredName() );
				indexByUNameIdx.put( keyUNameIdx, keepObj );
			}

			if( indexByPrevIdx != null ) {
				ICFBamDelTopDepByPrevIdxKey keyPrevIdx =
					schema.getCFBamBackingStore().getFactoryDelTopDep().newByPrevIdxKey();
				keyPrevIdx.setOptionalPrevId( keepObj.getOptionalPrevId() );
				Map<CFLibDbKeyHash256, ICFBamDelTopDepObj > mapPrevIdx = indexByPrevIdx.get( keyPrevIdx );
				if( mapPrevIdx != null ) {
					mapPrevIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByNextIdx != null ) {
				ICFBamDelTopDepByNextIdxKey keyNextIdx =
					schema.getCFBamBackingStore().getFactoryDelTopDep().newByNextIdxKey();
				keyNextIdx.setOptionalNextId( keepObj.getOptionalNextId() );
				Map<CFLibDbKeyHash256, ICFBamDelTopDepObj > mapNextIdx = indexByNextIdx.get( keyNextIdx );
				if( mapNextIdx != null ) {
					mapNextIdx.put( keepObj.getPKey(), keepObj );
				}
			}

		}
		return( keepObj );
	}

	@Override
	public ICFBamDelTopDepObj createDelTopDep( ICFBamDelTopDepObj Obj ) {
		ICFBamDelTopDepObj obj = Obj;
		ICFBamDelTopDep rec = obj.getDelTopDepRec();
		schema.getCFBamBackingStore().getTableDelTopDep().createDelTopDep(
			null,
			rec );
		obj.copyRecToPKey();
		if( obj.getClassCode() == runtimeClassCode ) {
			obj = (ICFBamDelTopDepObj)(obj.realise());
		}
		ICFBamDelTopDepObj prev = obj.getOptionalLookupPrev();
		if( prev != null ) {
			prev.read( true );
		}
		obj.endEdit();
		return( obj );
	}

	@Override
	public ICFBamDelTopDepObj readDelTopDep( CFLibDbKeyHash256 pkey ) {
		return( readDelTopDep( pkey, false ) );
	}

	@Override
	public ICFBamDelTopDepObj readDelTopDep( CFLibDbKeyHash256 pkey, boolean forceRead ) {
		ICFBamDelTopDepObj obj = null;
		if( ( ! forceRead ) && members.containsKey( pkey ) ) {
			obj = members.get( pkey );
		}
		else {
			ICFBamDelTopDep readRec = schema.getCFBamBackingStore().getTableDelTopDep().readDerivedByIdIdx( null,
						pkey );
			if( readRec != null ) {
				obj = (ICFBamDelTopDepObj)schema.getScopeTableObj().constructByClassCode( readRec.getClassCode() );
				obj.setPKey( readRec.getPKey() );
				obj.setRec( readRec );
				obj = (ICFBamDelTopDepObj)obj.realise();
			}
		}
		return( obj );
	}

	@Override
	public ICFBamDelTopDepObj readCachedDelTopDep( CFLibDbKeyHash256 pkey ) {
		ICFBamDelTopDepObj obj = null;
		if( members.containsKey( pkey ) ) {
			obj = members.get( pkey );
		}
		return( obj );
	}

	@Override
	public void reallyDeepDisposeDelTopDep( ICFBamDelTopDepObj obj )
	{
		final String S_ProcName = "CFBamDelTopDepTableObj.reallyDeepDisposeDelTopDep() ";
		String classCode;
		if( obj == null ) {
			return;
		}
		CFLibDbKeyHash256 pkey = obj.getPKey();
		ICFBamDelTopDepObj existing = readCachedDelTopDep( pkey );
		if( existing == null ) {
			return;
		}
		members.remove( pkey );
		ICFBamDelTopDepByDelTopDepTblIdxKey keyDelTopDepTblIdx = schema.getCFBamBackingStore().getFactoryDelTopDep().newByDelTopDepTblIdxKey();
		keyDelTopDepTblIdx.setRequiredTableId( existing.getRequiredTableId() );

		ICFBamDelTopDepByUNameIdxKey keyUNameIdx = schema.getCFBamBackingStore().getFactoryDelTopDep().newByUNameIdxKey();
		keyUNameIdx.setRequiredTableId( existing.getRequiredTableId() );
		keyUNameIdx.setRequiredName( existing.getRequiredName() );

		ICFBamDelTopDepByPrevIdxKey keyPrevIdx = schema.getCFBamBackingStore().getFactoryDelTopDep().newByPrevIdxKey();
		keyPrevIdx.setOptionalPrevId( existing.getOptionalPrevId() );

		ICFBamDelTopDepByNextIdxKey keyNextIdx = schema.getCFBamBackingStore().getFactoryDelTopDep().newByNextIdxKey();
		keyNextIdx.setOptionalNextId( existing.getOptionalNextId() );


		schema.getDelSubDep1TableObj().deepDisposeDelSubDep1ByDelTopDepIdx( existing.getRequiredId() );

		if( indexByDelTopDepTblIdx != null ) {
			if( indexByDelTopDepTblIdx.containsKey( keyDelTopDepTblIdx ) ) {
				indexByDelTopDepTblIdx.get( keyDelTopDepTblIdx ).remove( pkey );
				if( indexByDelTopDepTblIdx.get( keyDelTopDepTblIdx ).size() <= 0 ) {
					indexByDelTopDepTblIdx.remove( keyDelTopDepTblIdx );
				}
			}
		}

		if( indexByUNameIdx != null ) {
			indexByUNameIdx.remove( keyUNameIdx );
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


		schema.getDelDepTableObj().reallyDeepDisposeDelDep( obj );
	}
	@Override
	public void deepDisposeDelTopDep( CFLibDbKeyHash256 pkey ) {
		ICFBamDelTopDepObj obj = readCachedDelTopDep( pkey );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public ICFBamDelTopDepObj lockDelTopDep( CFLibDbKeyHash256 pkey ) {
		ICFBamDelTopDepObj locked = null;
		ICFBamDelTopDep lockRec = schema.getCFBamBackingStore().getTableDelTopDep().lockDerived( null, pkey );
		if( lockRec != null ) {
				locked = (ICFBamDelTopDepObj)schema.getScopeTableObj().constructByClassCode( lockRec.getClassCode() );
			locked.setRec( lockRec );
			locked.setPKey( lockRec.getPKey() );
			locked = (ICFBamDelTopDepObj)locked.realise();
		}
		else {
			throw new CFLibCollisionDetectedException( getClass(), "lockDelTopDep", pkey );
		}
		return( locked );
	}

	@Override
	public List<ICFBamDelTopDepObj> readAllDelTopDep() {
		return( readAllDelTopDep( false ) );
	}

	@Override
	public List<ICFBamDelTopDepObj> readAllDelTopDep( boolean forceRead ) {
		final String S_ProcName = "readAllDelTopDep";
		if( ( allDelTopDep == null ) || forceRead ) {
			Map<CFLibDbKeyHash256, ICFBamDelTopDepObj> map = new HashMap<CFLibDbKeyHash256,ICFBamDelTopDepObj>();
			allDelTopDep = map;
			ICFBamDelTopDep[] recList = schema.getCFBamBackingStore().getTableDelTopDep().readAllDerived( null );
			ICFBamDelTopDep rec;
			ICFBamDelTopDepObj obj;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamDelTopDepObj)schema.getScopeTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamDelTopDepObj realised = (ICFBamDelTopDepObj)obj.realise();
			}
		}
		int len = allDelTopDep.size();
		ICFBamDelTopDepObj arr[] = new ICFBamDelTopDepObj[len];
		Iterator<ICFBamDelTopDepObj> valIter = allDelTopDep.values().iterator();
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
		ArrayList<ICFBamDelTopDepObj> arrayList = new ArrayList<ICFBamDelTopDepObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamDelTopDepObj> cmp = new Comparator<ICFBamDelTopDepObj>() {
			@Override
			public int compare( ICFBamDelTopDepObj lhs, ICFBamDelTopDepObj rhs ) {
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
		List<ICFBamDelTopDepObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFBamDelTopDepObj> readCachedAllDelTopDep() {
		final String S_ProcName = "readCachedAllDelTopDep";
		ArrayList<ICFBamDelTopDepObj> arrayList = new ArrayList<ICFBamDelTopDepObj>();
		if( allDelTopDep != null ) {
			int len = allDelTopDep.size();
			ICFBamDelTopDepObj arr[] = new ICFBamDelTopDepObj[len];
			Iterator<ICFBamDelTopDepObj> valIter = allDelTopDep.values().iterator();
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
		Comparator<ICFBamDelTopDepObj> cmp = new Comparator<ICFBamDelTopDepObj>() {
			public int compare( ICFBamDelTopDepObj lhs, ICFBamDelTopDepObj rhs ) {
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
	public ICFBamDelTopDepObj readDelTopDepByIdIdx( CFLibDbKeyHash256 Id )
	{
		return( readDelTopDepByIdIdx( Id,
			false ) );
	}

	@Override
	public ICFBamDelTopDepObj readDelTopDepByIdIdx( CFLibDbKeyHash256 Id, boolean forceRead )
	{
		ICFBamDelTopDepObj obj = readDelTopDep( Id, forceRead );
		return( obj );
	}

	@Override
	public List<ICFBamDelTopDepObj> readDelTopDepByTenantIdx( CFLibDbKeyHash256 TenantId )
	{
		return( readDelTopDepByTenantIdx( TenantId,
			false ) );
	}

	@Override
	public List<ICFBamDelTopDepObj> readDelTopDepByTenantIdx( CFLibDbKeyHash256 TenantId,
		boolean forceRead )
	{
		final String S_ProcName = "readDelTopDepByTenantIdx";
		ICFBamScopeByTenantIdxKey key = schema.getCFBamBackingStore().getFactoryScope().newByTenantIdxKey();
		key.setRequiredTenantId( TenantId );
		Map<CFLibDbKeyHash256, ICFBamDelTopDepObj> dict;
		if( indexByTenantIdx == null ) {
			indexByTenantIdx = new HashMap< ICFBamScopeByTenantIdxKey,
				Map< CFLibDbKeyHash256, ICFBamDelTopDepObj > >();
		}
		if( ( ! forceRead ) && indexByTenantIdx.containsKey( key ) ) {
			dict = indexByTenantIdx.get( key );
		}
		else {
			dict = new HashMap<CFLibDbKeyHash256, ICFBamDelTopDepObj>();
			ICFBamScopeObj obj;
			ICFBamScope[] recList = schema.getCFBamBackingStore().getTableScope().readDerivedByTenantIdx( null,
				TenantId );
			ICFBamScope rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamDelTopDepObj)schema.getScopeTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamDelTopDepObj realised = (ICFBamDelTopDepObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByTenantIdx.put( key, dict );
		}
		int len = dict.size();
		ICFBamDelTopDepObj arr[] = new ICFBamDelTopDepObj[len];
		Iterator<ICFBamDelTopDepObj> valIter = dict.values().iterator();
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
		ArrayList<ICFBamDelTopDepObj> arrayList = new ArrayList<ICFBamDelTopDepObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamDelTopDepObj> cmp = new Comparator<ICFBamDelTopDepObj>() {
			@Override
			public int compare( ICFBamDelTopDepObj lhs, ICFBamDelTopDepObj rhs ) {
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
		List<ICFBamDelTopDepObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFBamDelTopDepObj> readDelTopDepByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId )
	{
		return( readDelTopDepByDefSchemaIdx( DefSchemaId,
			false ) );
	}

	@Override
	public List<ICFBamDelTopDepObj> readDelTopDepByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId,
		boolean forceRead )
	{
		final String S_ProcName = "readDelTopDepByDefSchemaIdx";
		ICFBamDelDepByDefSchemaIdxKey key = schema.getCFBamBackingStore().getFactoryDelDep().newByDefSchemaIdxKey();
		key.setOptionalDefSchemaId( DefSchemaId );
		Map<CFLibDbKeyHash256, ICFBamDelTopDepObj> dict;
		if( indexByDefSchemaIdx == null ) {
			indexByDefSchemaIdx = new HashMap< ICFBamDelDepByDefSchemaIdxKey,
				Map< CFLibDbKeyHash256, ICFBamDelTopDepObj > >();
		}
		if( ( ! forceRead ) && indexByDefSchemaIdx.containsKey( key ) ) {
			dict = indexByDefSchemaIdx.get( key );
		}
		else {
			dict = new HashMap<CFLibDbKeyHash256, ICFBamDelTopDepObj>();
			ICFBamDelDepObj obj;
			ICFBamDelDep[] recList = schema.getCFBamBackingStore().getTableDelDep().readDerivedByDefSchemaIdx( null,
				DefSchemaId );
			ICFBamDelDep rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamDelTopDepObj)schema.getScopeTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamDelTopDepObj realised = (ICFBamDelTopDepObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByDefSchemaIdx.put( key, dict );
		}
		int len = dict.size();
		ICFBamDelTopDepObj arr[] = new ICFBamDelTopDepObj[len];
		Iterator<ICFBamDelTopDepObj> valIter = dict.values().iterator();
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
		ArrayList<ICFBamDelTopDepObj> arrayList = new ArrayList<ICFBamDelTopDepObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamDelTopDepObj> cmp = new Comparator<ICFBamDelTopDepObj>() {
			@Override
			public int compare( ICFBamDelTopDepObj lhs, ICFBamDelTopDepObj rhs ) {
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
		List<ICFBamDelTopDepObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFBamDelTopDepObj> readDelTopDepByDelDepIdx( CFLibDbKeyHash256 RelationId )
	{
		return( readDelTopDepByDelDepIdx( RelationId,
			false ) );
	}

	@Override
	public List<ICFBamDelTopDepObj> readDelTopDepByDelDepIdx( CFLibDbKeyHash256 RelationId,
		boolean forceRead )
	{
		final String S_ProcName = "readDelTopDepByDelDepIdx";
		ICFBamDelDepByDelDepIdxKey key = schema.getCFBamBackingStore().getFactoryDelDep().newByDelDepIdxKey();
		key.setRequiredRelationId( RelationId );
		Map<CFLibDbKeyHash256, ICFBamDelTopDepObj> dict;
		if( indexByDelDepIdx == null ) {
			indexByDelDepIdx = new HashMap< ICFBamDelDepByDelDepIdxKey,
				Map< CFLibDbKeyHash256, ICFBamDelTopDepObj > >();
		}
		if( ( ! forceRead ) && indexByDelDepIdx.containsKey( key ) ) {
			dict = indexByDelDepIdx.get( key );
		}
		else {
			dict = new HashMap<CFLibDbKeyHash256, ICFBamDelTopDepObj>();
			ICFBamDelDepObj obj;
			ICFBamDelDep[] recList = schema.getCFBamBackingStore().getTableDelDep().readDerivedByDelDepIdx( null,
				RelationId );
			ICFBamDelDep rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamDelTopDepObj)schema.getScopeTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamDelTopDepObj realised = (ICFBamDelTopDepObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByDelDepIdx.put( key, dict );
		}
		int len = dict.size();
		ICFBamDelTopDepObj arr[] = new ICFBamDelTopDepObj[len];
		Iterator<ICFBamDelTopDepObj> valIter = dict.values().iterator();
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
		ArrayList<ICFBamDelTopDepObj> arrayList = new ArrayList<ICFBamDelTopDepObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamDelTopDepObj> cmp = new Comparator<ICFBamDelTopDepObj>() {
			@Override
			public int compare( ICFBamDelTopDepObj lhs, ICFBamDelTopDepObj rhs ) {
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
		List<ICFBamDelTopDepObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFBamDelTopDepObj> readDelTopDepByDelTopDepTblIdx( CFLibDbKeyHash256 TableId )
	{
		return( readDelTopDepByDelTopDepTblIdx( TableId,
			false ) );
	}

	@Override
	public List<ICFBamDelTopDepObj> readDelTopDepByDelTopDepTblIdx( CFLibDbKeyHash256 TableId,
		boolean forceRead )
	{
		final String S_ProcName = "readDelTopDepByDelTopDepTblIdx";
		ICFBamDelTopDepByDelTopDepTblIdxKey key = schema.getCFBamBackingStore().getFactoryDelTopDep().newByDelTopDepTblIdxKey();
		key.setRequiredTableId( TableId );
		Map<CFLibDbKeyHash256, ICFBamDelTopDepObj> dict;
		if( indexByDelTopDepTblIdx == null ) {
			indexByDelTopDepTblIdx = new HashMap< ICFBamDelTopDepByDelTopDepTblIdxKey,
				Map< CFLibDbKeyHash256, ICFBamDelTopDepObj > >();
		}
		if( ( ! forceRead ) && indexByDelTopDepTblIdx.containsKey( key ) ) {
			dict = indexByDelTopDepTblIdx.get( key );
		}
		else {
			dict = new HashMap<CFLibDbKeyHash256, ICFBamDelTopDepObj>();
			ICFBamDelTopDepObj obj;
			ICFBamDelTopDep[] recList = schema.getCFBamBackingStore().getTableDelTopDep().readDerivedByDelTopDepTblIdx( null,
				TableId );
			ICFBamDelTopDep rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamDelTopDepObj)schema.getScopeTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamDelTopDepObj realised = (ICFBamDelTopDepObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByDelTopDepTblIdx.put( key, dict );
		}
		int len = dict.size();
		ICFBamDelTopDepObj arr[] = new ICFBamDelTopDepObj[len];
		Iterator<ICFBamDelTopDepObj> valIter = dict.values().iterator();
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
		ArrayList<ICFBamDelTopDepObj> arrayList = new ArrayList<ICFBamDelTopDepObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamDelTopDepObj> cmp = new Comparator<ICFBamDelTopDepObj>() {
			@Override
			public int compare( ICFBamDelTopDepObj lhs, ICFBamDelTopDepObj rhs ) {
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
		List<ICFBamDelTopDepObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public ICFBamDelTopDepObj readDelTopDepByUNameIdx( CFLibDbKeyHash256 TableId,
		String Name )
	{
		return( readDelTopDepByUNameIdx( TableId,
			Name,
			false ) );
	}

	@Override
	public ICFBamDelTopDepObj readDelTopDepByUNameIdx( CFLibDbKeyHash256 TableId,
		String Name, boolean forceRead )
	{
		if( indexByUNameIdx == null ) {
			indexByUNameIdx = new HashMap< ICFBamDelTopDepByUNameIdxKey,
				ICFBamDelTopDepObj >();
		}
		ICFBamDelTopDepByUNameIdxKey key = schema.getCFBamBackingStore().getFactoryDelTopDep().newByUNameIdxKey();
		key.setRequiredTableId( TableId );
		key.setRequiredName( Name );
		ICFBamDelTopDepObj obj = null;
		if( ( ! forceRead ) && indexByUNameIdx.containsKey( key ) ) {
			obj = indexByUNameIdx.get( key );
		}
		else {
			ICFBamDelTopDep rec = schema.getCFBamBackingStore().getTableDelTopDep().readDerivedByUNameIdx( null,
				TableId,
				Name );
			if( rec != null ) {
				obj = (ICFBamDelTopDepObj)schema.getScopeTableObj().constructByClassCode( rec.getClassCode() );
				obj.setRec( rec );
				obj.setPKey( rec.getPKey() );
				obj = (ICFBamDelTopDepObj)obj.realise();
			}
		}
		return( obj );
	}

	@Override
	public List<ICFBamDelTopDepObj> readDelTopDepByPrevIdx( CFLibDbKeyHash256 PrevId )
	{
		return( readDelTopDepByPrevIdx( PrevId,
			false ) );
	}

	@Override
	public List<ICFBamDelTopDepObj> readDelTopDepByPrevIdx( CFLibDbKeyHash256 PrevId,
		boolean forceRead )
	{
		final String S_ProcName = "readDelTopDepByPrevIdx";
		ICFBamDelTopDepByPrevIdxKey key = schema.getCFBamBackingStore().getFactoryDelTopDep().newByPrevIdxKey();
		key.setOptionalPrevId( PrevId );
		Map<CFLibDbKeyHash256, ICFBamDelTopDepObj> dict;
		if( indexByPrevIdx == null ) {
			indexByPrevIdx = new HashMap< ICFBamDelTopDepByPrevIdxKey,
				Map< CFLibDbKeyHash256, ICFBamDelTopDepObj > >();
		}
		if( ( ! forceRead ) && indexByPrevIdx.containsKey( key ) ) {
			dict = indexByPrevIdx.get( key );
		}
		else {
			dict = new HashMap<CFLibDbKeyHash256, ICFBamDelTopDepObj>();
			ICFBamDelTopDepObj obj;
			ICFBamDelTopDep[] recList = schema.getCFBamBackingStore().getTableDelTopDep().readDerivedByPrevIdx( null,
				PrevId );
			ICFBamDelTopDep rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamDelTopDepObj)schema.getScopeTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamDelTopDepObj realised = (ICFBamDelTopDepObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByPrevIdx.put( key, dict );
		}
		int len = dict.size();
		ICFBamDelTopDepObj arr[] = new ICFBamDelTopDepObj[len];
		Iterator<ICFBamDelTopDepObj> valIter = dict.values().iterator();
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
		ArrayList<ICFBamDelTopDepObj> arrayList = new ArrayList<ICFBamDelTopDepObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamDelTopDepObj> cmp = new Comparator<ICFBamDelTopDepObj>() {
			@Override
			public int compare( ICFBamDelTopDepObj lhs, ICFBamDelTopDepObj rhs ) {
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
		List<ICFBamDelTopDepObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFBamDelTopDepObj> readDelTopDepByNextIdx( CFLibDbKeyHash256 NextId )
	{
		return( readDelTopDepByNextIdx( NextId,
			false ) );
	}

	@Override
	public List<ICFBamDelTopDepObj> readDelTopDepByNextIdx( CFLibDbKeyHash256 NextId,
		boolean forceRead )
	{
		final String S_ProcName = "readDelTopDepByNextIdx";
		ICFBamDelTopDepByNextIdxKey key = schema.getCFBamBackingStore().getFactoryDelTopDep().newByNextIdxKey();
		key.setOptionalNextId( NextId );
		Map<CFLibDbKeyHash256, ICFBamDelTopDepObj> dict;
		if( indexByNextIdx == null ) {
			indexByNextIdx = new HashMap< ICFBamDelTopDepByNextIdxKey,
				Map< CFLibDbKeyHash256, ICFBamDelTopDepObj > >();
		}
		if( ( ! forceRead ) && indexByNextIdx.containsKey( key ) ) {
			dict = indexByNextIdx.get( key );
		}
		else {
			dict = new HashMap<CFLibDbKeyHash256, ICFBamDelTopDepObj>();
			ICFBamDelTopDepObj obj;
			ICFBamDelTopDep[] recList = schema.getCFBamBackingStore().getTableDelTopDep().readDerivedByNextIdx( null,
				NextId );
			ICFBamDelTopDep rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamDelTopDepObj)schema.getScopeTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamDelTopDepObj realised = (ICFBamDelTopDepObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByNextIdx.put( key, dict );
		}
		int len = dict.size();
		ICFBamDelTopDepObj arr[] = new ICFBamDelTopDepObj[len];
		Iterator<ICFBamDelTopDepObj> valIter = dict.values().iterator();
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
		ArrayList<ICFBamDelTopDepObj> arrayList = new ArrayList<ICFBamDelTopDepObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamDelTopDepObj> cmp = new Comparator<ICFBamDelTopDepObj>() {
			@Override
			public int compare( ICFBamDelTopDepObj lhs, ICFBamDelTopDepObj rhs ) {
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
		List<ICFBamDelTopDepObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public ICFBamDelTopDepObj readCachedDelTopDepByIdIdx( CFLibDbKeyHash256 Id )
	{
		ICFBamDelTopDepObj obj = null;
		obj = readCachedDelTopDep( Id );
		return( obj );
	}

	@Override
	public List<ICFBamDelTopDepObj> readCachedDelTopDepByTenantIdx( CFLibDbKeyHash256 TenantId )
	{
		final String S_ProcName = "readCachedDelTopDepByTenantIdx";
		ICFBamScopeByTenantIdxKey key = schema.getCFBamBackingStore().getFactoryScope().newByTenantIdxKey();
		key.setRequiredTenantId( TenantId );
		ArrayList<ICFBamDelTopDepObj> arrayList = new ArrayList<ICFBamDelTopDepObj>();
		if( indexByTenantIdx != null ) {
			Map<CFLibDbKeyHash256, ICFBamDelTopDepObj> dict;
			if( indexByTenantIdx.containsKey( key ) ) {
				dict = indexByTenantIdx.get( key );
				int len = dict.size();
				ICFBamDelTopDepObj arr[] = new ICFBamDelTopDepObj[len];
				Iterator<ICFBamDelTopDepObj> valIter = dict.values().iterator();
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
			ICFBamDelTopDepObj obj;
			Iterator<ICFBamDelTopDepObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFBamDelTopDepObj> cmp = new Comparator<ICFBamDelTopDepObj>() {
			@Override
			public int compare( ICFBamDelTopDepObj lhs, ICFBamDelTopDepObj rhs ) {
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
	public List<ICFBamDelTopDepObj> readCachedDelTopDepByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId )
	{
		final String S_ProcName = "readCachedDelTopDepByDefSchemaIdx";
		ICFBamDelDepByDefSchemaIdxKey key = schema.getCFBamBackingStore().getFactoryDelDep().newByDefSchemaIdxKey();
		key.setOptionalDefSchemaId( DefSchemaId );
		ArrayList<ICFBamDelTopDepObj> arrayList = new ArrayList<ICFBamDelTopDepObj>();
		if( indexByDefSchemaIdx != null ) {
			Map<CFLibDbKeyHash256, ICFBamDelTopDepObj> dict;
			if( indexByDefSchemaIdx.containsKey( key ) ) {
				dict = indexByDefSchemaIdx.get( key );
				int len = dict.size();
				ICFBamDelTopDepObj arr[] = new ICFBamDelTopDepObj[len];
				Iterator<ICFBamDelTopDepObj> valIter = dict.values().iterator();
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
			ICFBamDelTopDepObj obj;
			Iterator<ICFBamDelTopDepObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFBamDelTopDepObj> cmp = new Comparator<ICFBamDelTopDepObj>() {
			@Override
			public int compare( ICFBamDelTopDepObj lhs, ICFBamDelTopDepObj rhs ) {
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
	public List<ICFBamDelTopDepObj> readCachedDelTopDepByDelDepIdx( CFLibDbKeyHash256 RelationId )
	{
		final String S_ProcName = "readCachedDelTopDepByDelDepIdx";
		ICFBamDelDepByDelDepIdxKey key = schema.getCFBamBackingStore().getFactoryDelDep().newByDelDepIdxKey();
		key.setRequiredRelationId( RelationId );
		ArrayList<ICFBamDelTopDepObj> arrayList = new ArrayList<ICFBamDelTopDepObj>();
		if( indexByDelDepIdx != null ) {
			Map<CFLibDbKeyHash256, ICFBamDelTopDepObj> dict;
			if( indexByDelDepIdx.containsKey( key ) ) {
				dict = indexByDelDepIdx.get( key );
				int len = dict.size();
				ICFBamDelTopDepObj arr[] = new ICFBamDelTopDepObj[len];
				Iterator<ICFBamDelTopDepObj> valIter = dict.values().iterator();
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
			ICFBamDelTopDepObj obj;
			Iterator<ICFBamDelTopDepObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFBamDelTopDepObj> cmp = new Comparator<ICFBamDelTopDepObj>() {
			@Override
			public int compare( ICFBamDelTopDepObj lhs, ICFBamDelTopDepObj rhs ) {
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
	public List<ICFBamDelTopDepObj> readCachedDelTopDepByDelTopDepTblIdx( CFLibDbKeyHash256 TableId )
	{
		final String S_ProcName = "readCachedDelTopDepByDelTopDepTblIdx";
		ICFBamDelTopDepByDelTopDepTblIdxKey key = schema.getCFBamBackingStore().getFactoryDelTopDep().newByDelTopDepTblIdxKey();
		key.setRequiredTableId( TableId );
		ArrayList<ICFBamDelTopDepObj> arrayList = new ArrayList<ICFBamDelTopDepObj>();
		if( indexByDelTopDepTblIdx != null ) {
			Map<CFLibDbKeyHash256, ICFBamDelTopDepObj> dict;
			if( indexByDelTopDepTblIdx.containsKey( key ) ) {
				dict = indexByDelTopDepTblIdx.get( key );
				int len = dict.size();
				ICFBamDelTopDepObj arr[] = new ICFBamDelTopDepObj[len];
				Iterator<ICFBamDelTopDepObj> valIter = dict.values().iterator();
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
			ICFBamDelTopDepObj obj;
			Iterator<ICFBamDelTopDepObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFBamDelTopDepObj> cmp = new Comparator<ICFBamDelTopDepObj>() {
			@Override
			public int compare( ICFBamDelTopDepObj lhs, ICFBamDelTopDepObj rhs ) {
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
	public ICFBamDelTopDepObj readCachedDelTopDepByUNameIdx( CFLibDbKeyHash256 TableId,
		String Name )
	{
		ICFBamDelTopDepObj obj = null;
		ICFBamDelTopDepByUNameIdxKey key = schema.getCFBamBackingStore().getFactoryDelTopDep().newByUNameIdxKey();
		key.setRequiredTableId( TableId );
		key.setRequiredName( Name );
		if( indexByUNameIdx != null ) {
			if( indexByUNameIdx.containsKey( key ) ) {
				obj = indexByUNameIdx.get( key );
			}
			else {
				Iterator<ICFBamDelTopDepObj> valIter = members.values().iterator();
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
			Iterator<ICFBamDelTopDepObj> valIter = members.values().iterator();
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
	public List<ICFBamDelTopDepObj> readCachedDelTopDepByPrevIdx( CFLibDbKeyHash256 PrevId )
	{
		final String S_ProcName = "readCachedDelTopDepByPrevIdx";
		ICFBamDelTopDepByPrevIdxKey key = schema.getCFBamBackingStore().getFactoryDelTopDep().newByPrevIdxKey();
		key.setOptionalPrevId( PrevId );
		ArrayList<ICFBamDelTopDepObj> arrayList = new ArrayList<ICFBamDelTopDepObj>();
		if( indexByPrevIdx != null ) {
			Map<CFLibDbKeyHash256, ICFBamDelTopDepObj> dict;
			if( indexByPrevIdx.containsKey( key ) ) {
				dict = indexByPrevIdx.get( key );
				int len = dict.size();
				ICFBamDelTopDepObj arr[] = new ICFBamDelTopDepObj[len];
				Iterator<ICFBamDelTopDepObj> valIter = dict.values().iterator();
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
			ICFBamDelTopDepObj obj;
			Iterator<ICFBamDelTopDepObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFBamDelTopDepObj> cmp = new Comparator<ICFBamDelTopDepObj>() {
			@Override
			public int compare( ICFBamDelTopDepObj lhs, ICFBamDelTopDepObj rhs ) {
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
	public List<ICFBamDelTopDepObj> readCachedDelTopDepByNextIdx( CFLibDbKeyHash256 NextId )
	{
		final String S_ProcName = "readCachedDelTopDepByNextIdx";
		ICFBamDelTopDepByNextIdxKey key = schema.getCFBamBackingStore().getFactoryDelTopDep().newByNextIdxKey();
		key.setOptionalNextId( NextId );
		ArrayList<ICFBamDelTopDepObj> arrayList = new ArrayList<ICFBamDelTopDepObj>();
		if( indexByNextIdx != null ) {
			Map<CFLibDbKeyHash256, ICFBamDelTopDepObj> dict;
			if( indexByNextIdx.containsKey( key ) ) {
				dict = indexByNextIdx.get( key );
				int len = dict.size();
				ICFBamDelTopDepObj arr[] = new ICFBamDelTopDepObj[len];
				Iterator<ICFBamDelTopDepObj> valIter = dict.values().iterator();
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
			ICFBamDelTopDepObj obj;
			Iterator<ICFBamDelTopDepObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFBamDelTopDepObj> cmp = new Comparator<ICFBamDelTopDepObj>() {
			@Override
			public int compare( ICFBamDelTopDepObj lhs, ICFBamDelTopDepObj rhs ) {
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
	public void deepDisposeDelTopDepByIdIdx( CFLibDbKeyHash256 Id )
	{
		ICFBamDelTopDepObj obj = readCachedDelTopDepByIdIdx( Id );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public void deepDisposeDelTopDepByTenantIdx( CFLibDbKeyHash256 TenantId )
	{
		final String S_ProcName = "deepDisposeDelTopDepByTenantIdx";
		ICFBamDelTopDepObj obj;
		List<ICFBamDelTopDepObj> arrayList = readCachedDelTopDepByTenantIdx( TenantId );
		if( arrayList != null )  {
			Iterator<ICFBamDelTopDepObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public void deepDisposeDelTopDepByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId )
	{
		final String S_ProcName = "deepDisposeDelTopDepByDefSchemaIdx";
		ICFBamDelTopDepObj obj;
		List<ICFBamDelTopDepObj> arrayList = readCachedDelTopDepByDefSchemaIdx( DefSchemaId );
		if( arrayList != null )  {
			Iterator<ICFBamDelTopDepObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public void deepDisposeDelTopDepByDelDepIdx( CFLibDbKeyHash256 RelationId )
	{
		final String S_ProcName = "deepDisposeDelTopDepByDelDepIdx";
		ICFBamDelTopDepObj obj;
		List<ICFBamDelTopDepObj> arrayList = readCachedDelTopDepByDelDepIdx( RelationId );
		if( arrayList != null )  {
			Iterator<ICFBamDelTopDepObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public void deepDisposeDelTopDepByDelTopDepTblIdx( CFLibDbKeyHash256 TableId )
	{
		final String S_ProcName = "deepDisposeDelTopDepByDelTopDepTblIdx";
		ICFBamDelTopDepObj obj;
		List<ICFBamDelTopDepObj> arrayList = readCachedDelTopDepByDelTopDepTblIdx( TableId );
		if( arrayList != null )  {
			Iterator<ICFBamDelTopDepObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public void deepDisposeDelTopDepByUNameIdx( CFLibDbKeyHash256 TableId,
		String Name )
	{
		ICFBamDelTopDepObj obj = readCachedDelTopDepByUNameIdx( TableId,
				Name );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public void deepDisposeDelTopDepByPrevIdx( CFLibDbKeyHash256 PrevId )
	{
		final String S_ProcName = "deepDisposeDelTopDepByPrevIdx";
		ICFBamDelTopDepObj obj;
		List<ICFBamDelTopDepObj> arrayList = readCachedDelTopDepByPrevIdx( PrevId );
		if( arrayList != null )  {
			Iterator<ICFBamDelTopDepObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public void deepDisposeDelTopDepByNextIdx( CFLibDbKeyHash256 NextId )
	{
		final String S_ProcName = "deepDisposeDelTopDepByNextIdx";
		ICFBamDelTopDepObj obj;
		List<ICFBamDelTopDepObj> arrayList = readCachedDelTopDepByNextIdx( NextId );
		if( arrayList != null )  {
			Iterator<ICFBamDelTopDepObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public ICFBamDelTopDepObj updateDelTopDep( ICFBamDelTopDepObj Obj ) {
		ICFBamDelTopDepObj obj = Obj;
		schema.getCFBamBackingStore().getTableDelTopDep().updateDelTopDep( null,
			Obj.getDelTopDepRec() );
		if( Obj.getClassCode() == ((ICFBamSchemaObj)getSchema()).getDelTopDepTableObj().getClassCode() ) {
			obj = (ICFBamDelTopDepObj)Obj.realise();
		}
		obj.endEdit();
		return( obj );
	}

	@Override
	public void deleteDelTopDep( ICFBamDelTopDepObj Obj ) {
		ICFBamDelTopDepObj obj = Obj;
		ICFBamDelTopDepObj prev = obj.getOptionalLookupPrev();
		ICFBamDelTopDepObj next = obj.getOptionalLookupNext();
		schema.getCFBamBackingStore().getTableDelTopDep().deleteDelTopDep( null,
			obj.getDelTopDepRec() );
		Obj.forget();
		if( prev != null ) {
			prev.read( true );
		}
		if( next != null ) {
			next.read( true );
		}
	}

	@Override
	public void deleteDelTopDepByIdIdx( CFLibDbKeyHash256 Id )
	{
		ICFBamDelTopDepObj obj = readDelTopDep(Id);
		if( obj != null ) {
			ICFBamDelTopDepEditObj editObj = (ICFBamDelTopDepEditObj)obj.getEdit();
			boolean editStarted;
			if( editObj == null ) {
				editObj = (ICFBamDelTopDepEditObj)obj.beginEdit();
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
		deepDisposeDelTopDepByIdIdx( Id );
	}

	@Override
	public void deleteDelTopDepByTenantIdx( CFLibDbKeyHash256 TenantId )
	{
		ICFBamScopeByTenantIdxKey key = schema.getCFBamBackingStore().getFactoryScope().newByTenantIdxKey();
		key.setRequiredTenantId( TenantId );
		if( indexByTenantIdx == null ) {
			indexByTenantIdx = new HashMap< ICFBamScopeByTenantIdxKey,
				Map< CFLibDbKeyHash256, ICFBamDelTopDepObj > >();
		}
		if( indexByTenantIdx.containsKey( key ) ) {
			Map<CFLibDbKeyHash256, ICFBamDelTopDepObj> dict = indexByTenantIdx.get( key );
			schema.getCFBamBackingStore().getTableDelTopDep().deleteDelTopDepByTenantIdx( null,
				TenantId );
			Iterator<ICFBamDelTopDepObj> iter = dict.values().iterator();
			ICFBamDelTopDepObj obj;
			List<ICFBamDelTopDepObj> toForget = new LinkedList<ICFBamDelTopDepObj>();
			while( iter.hasNext() ) {
				obj = iter.next();
				toForget.add( obj );
			}
			iter = toForget.iterator();
			while( iter.hasNext() ) {
				obj = iter.next();
				obj.forget();
			}
			indexByTenantIdx.remove( key );
		}
		else {
			schema.getCFBamBackingStore().getTableDelTopDep().deleteDelTopDepByTenantIdx( null,
				TenantId );
		}
		deepDisposeDelTopDepByTenantIdx( TenantId );
	}

	@Override
	public void deleteDelTopDepByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId )
	{
		ICFBamDelDepByDefSchemaIdxKey key = schema.getCFBamBackingStore().getFactoryDelDep().newByDefSchemaIdxKey();
		key.setOptionalDefSchemaId( DefSchemaId );
		if( indexByDefSchemaIdx == null ) {
			indexByDefSchemaIdx = new HashMap< ICFBamDelDepByDefSchemaIdxKey,
				Map< CFLibDbKeyHash256, ICFBamDelTopDepObj > >();
		}
		if( indexByDefSchemaIdx.containsKey( key ) ) {
			Map<CFLibDbKeyHash256, ICFBamDelTopDepObj> dict = indexByDefSchemaIdx.get( key );
			schema.getCFBamBackingStore().getTableDelTopDep().deleteDelTopDepByDefSchemaIdx( null,
				DefSchemaId );
			Iterator<ICFBamDelTopDepObj> iter = dict.values().iterator();
			ICFBamDelTopDepObj obj;
			List<ICFBamDelTopDepObj> toForget = new LinkedList<ICFBamDelTopDepObj>();
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
			schema.getCFBamBackingStore().getTableDelTopDep().deleteDelTopDepByDefSchemaIdx( null,
				DefSchemaId );
		}
		deepDisposeDelTopDepByDefSchemaIdx( DefSchemaId );
	}

	@Override
	public void deleteDelTopDepByDelDepIdx( CFLibDbKeyHash256 RelationId )
	{
		ICFBamDelDepByDelDepIdxKey key = schema.getCFBamBackingStore().getFactoryDelDep().newByDelDepIdxKey();
		key.setRequiredRelationId( RelationId );
		if( indexByDelDepIdx == null ) {
			indexByDelDepIdx = new HashMap< ICFBamDelDepByDelDepIdxKey,
				Map< CFLibDbKeyHash256, ICFBamDelTopDepObj > >();
		}
		if( indexByDelDepIdx.containsKey( key ) ) {
			Map<CFLibDbKeyHash256, ICFBamDelTopDepObj> dict = indexByDelDepIdx.get( key );
			schema.getCFBamBackingStore().getTableDelTopDep().deleteDelTopDepByDelDepIdx( null,
				RelationId );
			Iterator<ICFBamDelTopDepObj> iter = dict.values().iterator();
			ICFBamDelTopDepObj obj;
			List<ICFBamDelTopDepObj> toForget = new LinkedList<ICFBamDelTopDepObj>();
			while( iter.hasNext() ) {
				obj = iter.next();
				toForget.add( obj );
			}
			iter = toForget.iterator();
			while( iter.hasNext() ) {
				obj = iter.next();
				obj.forget();
			}
			indexByDelDepIdx.remove( key );
		}
		else {
			schema.getCFBamBackingStore().getTableDelTopDep().deleteDelTopDepByDelDepIdx( null,
				RelationId );
		}
		deepDisposeDelTopDepByDelDepIdx( RelationId );
	}

	@Override
	public void deleteDelTopDepByDelTopDepTblIdx( CFLibDbKeyHash256 TableId )
	{
		ICFBamDelTopDepByDelTopDepTblIdxKey key = schema.getCFBamBackingStore().getFactoryDelTopDep().newByDelTopDepTblIdxKey();
		key.setRequiredTableId( TableId );
		if( indexByDelTopDepTblIdx == null ) {
			indexByDelTopDepTblIdx = new HashMap< ICFBamDelTopDepByDelTopDepTblIdxKey,
				Map< CFLibDbKeyHash256, ICFBamDelTopDepObj > >();
		}
		if( indexByDelTopDepTblIdx.containsKey( key ) ) {
			Map<CFLibDbKeyHash256, ICFBamDelTopDepObj> dict = indexByDelTopDepTblIdx.get( key );
			schema.getCFBamBackingStore().getTableDelTopDep().deleteDelTopDepByDelTopDepTblIdx( null,
				TableId );
			Iterator<ICFBamDelTopDepObj> iter = dict.values().iterator();
			ICFBamDelTopDepObj obj;
			List<ICFBamDelTopDepObj> toForget = new LinkedList<ICFBamDelTopDepObj>();
			while( iter.hasNext() ) {
				obj = iter.next();
				toForget.add( obj );
			}
			iter = toForget.iterator();
			while( iter.hasNext() ) {
				obj = iter.next();
				obj.forget();
			}
			indexByDelTopDepTblIdx.remove( key );
		}
		else {
			schema.getCFBamBackingStore().getTableDelTopDep().deleteDelTopDepByDelTopDepTblIdx( null,
				TableId );
		}
		deepDisposeDelTopDepByDelTopDepTblIdx( TableId );
	}

	@Override
	public void deleteDelTopDepByUNameIdx( CFLibDbKeyHash256 TableId,
		String Name )
	{
		if( indexByUNameIdx == null ) {
			indexByUNameIdx = new HashMap< ICFBamDelTopDepByUNameIdxKey,
				ICFBamDelTopDepObj >();
		}
		ICFBamDelTopDepByUNameIdxKey key = schema.getCFBamBackingStore().getFactoryDelTopDep().newByUNameIdxKey();
		key.setRequiredTableId( TableId );
		key.setRequiredName( Name );
		ICFBamDelTopDepObj obj = null;
		if( indexByUNameIdx.containsKey( key ) ) {
			obj = indexByUNameIdx.get( key );
			schema.getCFBamBackingStore().getTableDelTopDep().deleteDelTopDepByUNameIdx( null,
				TableId,
				Name );
			obj.forget();
		}
		else {
			schema.getCFBamBackingStore().getTableDelTopDep().deleteDelTopDepByUNameIdx( null,
				TableId,
				Name );
		}
		deepDisposeDelTopDepByUNameIdx( TableId,
				Name );
	}

	@Override
	public void deleteDelTopDepByPrevIdx( CFLibDbKeyHash256 PrevId )
	{
		ICFBamDelTopDepByPrevIdxKey key = schema.getCFBamBackingStore().getFactoryDelTopDep().newByPrevIdxKey();
		key.setOptionalPrevId( PrevId );
		if( indexByPrevIdx == null ) {
			indexByPrevIdx = new HashMap< ICFBamDelTopDepByPrevIdxKey,
				Map< CFLibDbKeyHash256, ICFBamDelTopDepObj > >();
		}
		if( indexByPrevIdx.containsKey( key ) ) {
			Map<CFLibDbKeyHash256, ICFBamDelTopDepObj> dict = indexByPrevIdx.get( key );
			schema.getCFBamBackingStore().getTableDelTopDep().deleteDelTopDepByPrevIdx( null,
				PrevId );
			Iterator<ICFBamDelTopDepObj> iter = dict.values().iterator();
			ICFBamDelTopDepObj obj;
			List<ICFBamDelTopDepObj> toForget = new LinkedList<ICFBamDelTopDepObj>();
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
			schema.getCFBamBackingStore().getTableDelTopDep().deleteDelTopDepByPrevIdx( null,
				PrevId );
		}
		deepDisposeDelTopDepByPrevIdx( PrevId );
	}

	@Override
	public void deleteDelTopDepByNextIdx( CFLibDbKeyHash256 NextId )
	{
		ICFBamDelTopDepByNextIdxKey key = schema.getCFBamBackingStore().getFactoryDelTopDep().newByNextIdxKey();
		key.setOptionalNextId( NextId );
		if( indexByNextIdx == null ) {
			indexByNextIdx = new HashMap< ICFBamDelTopDepByNextIdxKey,
				Map< CFLibDbKeyHash256, ICFBamDelTopDepObj > >();
		}
		if( indexByNextIdx.containsKey( key ) ) {
			Map<CFLibDbKeyHash256, ICFBamDelTopDepObj> dict = indexByNextIdx.get( key );
			schema.getCFBamBackingStore().getTableDelTopDep().deleteDelTopDepByNextIdx( null,
				NextId );
			Iterator<ICFBamDelTopDepObj> iter = dict.values().iterator();
			ICFBamDelTopDepObj obj;
			List<ICFBamDelTopDepObj> toForget = new LinkedList<ICFBamDelTopDepObj>();
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
			schema.getCFBamBackingStore().getTableDelTopDep().deleteDelTopDepByNextIdx( null,
				NextId );
		}
		deepDisposeDelTopDepByNextIdx( NextId );
	}

	/**
	 *	Move the CFBamDelTopDepObj instance up in the chain.  The instance is always refreshed.
	 *
	 *	@return	CFBamDelTopDepObj refreshed cache instance.
	 */
	@Override
	public ICFBamDelTopDepObj moveUpDelTopDep( ICFBamDelTopDepObj Obj ) {
		ICFBamDelTopDepObj obj = null;
		if( null != Obj.getEdit() ) {
			throw new CFLibCannotMoveEditedObjectException( getClass(),	"moveUpDelTopDep" );
		}
		ICFBamDelTopDep rec = schema.getCFBamBackingStore().getTableDelTopDep().moveRecUp( null,
			Obj.getRequiredId(),
			Obj.getRec().getRequiredRevision() );
		if( rec != null ) {
			obj = schema.getDelTopDepTableObj().newInstance();
			obj.setPKey( rec.getPKey() );
			obj.setRec( rec );
			obj = (ICFBamDelTopDepObj)obj.realise();
			ICFBamDelTopDepObj prev = obj.getOptionalLookupPrev( true );
			ICFBamDelTopDepObj next = obj.getOptionalLookupNext( true );
			if( next != null ) {
				ICFBamDelTopDepObj gnext = next.getOptionalLookupNext( true );
			}
		}
		return( obj );
	}

	/**
	 *	Move the CFBamDelTopDepObj instance down in the chain.  The instance is always refreshed.
	 *
	 *	@return	CFBamDelTopDepObj refreshed cache instance.
	 */
	@Override
	public ICFBamDelTopDepObj moveDownDelTopDep( ICFBamDelTopDepObj Obj ) {
		ICFBamDelTopDepObj obj = null;
		if( null != Obj.getEdit() ) {
			throw new CFLibCannotMoveEditedObjectException( getClass(),	"moveDownDelTopDep" );
		}
		ICFBamDelTopDep rec = schema.getCFBamBackingStore().getTableDelTopDep().moveRecDown( null,
			Obj.getRequiredId(),
			Obj.getRec().getRequiredRevision() );
		if( rec != null ) {
			obj = schema.getDelTopDepTableObj().newInstance();
			obj.setPKey( rec.getPKey() );
			obj.setRec( rec );
			obj = (ICFBamDelTopDepObj)obj.realise();
			ICFBamDelTopDepObj prev = obj.getOptionalLookupPrev( true );
			if( prev != null ) {
				ICFBamDelTopDepObj gprev = prev.getOptionalLookupPrev( true );
			}
			ICFBamDelTopDepObj next = obj.getOptionalLookupNext( true );
		}
		return( obj );
	}
}