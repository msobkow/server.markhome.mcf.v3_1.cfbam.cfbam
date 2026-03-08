// Description: Java 25 Table Object implementation for Relation.

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

public class CFBamRelationTableObj
	implements ICFBamRelationTableObj
{
	protected ICFBamSchemaObj schema;
	protected static int runtimeClassCode = ICFBamRelation.CLASS_CODE;
	protected static final int backingClassCode = ICFBamRelation.CLASS_CODE;
	private Map<CFLibDbKeyHash256, ICFBamRelationObj> members;
	private Map<CFLibDbKeyHash256, ICFBamRelationObj> allRelation;
	private Map< ICFBamScopeByTenantIdxKey,
		Map<CFLibDbKeyHash256, ICFBamRelationObj > > indexByTenantIdx;
	private Map< ICFBamRelationByUNameIdxKey,
		ICFBamRelationObj > indexByUNameIdx;
	private Map< ICFBamRelationByRelTableIdxKey,
		Map<CFLibDbKeyHash256, ICFBamRelationObj > > indexByRelTableIdx;
	private Map< ICFBamRelationByDefSchemaIdxKey,
		Map<CFLibDbKeyHash256, ICFBamRelationObj > > indexByDefSchemaIdx;
	private Map< ICFBamRelationByFromKeyIdxKey,
		Map<CFLibDbKeyHash256, ICFBamRelationObj > > indexByFromKeyIdx;
	private Map< ICFBamRelationByToTblIdxKey,
		Map<CFLibDbKeyHash256, ICFBamRelationObj > > indexByToTblIdx;
	private Map< ICFBamRelationByToKeyIdxKey,
		Map<CFLibDbKeyHash256, ICFBamRelationObj > > indexByToKeyIdx;
	private Map< ICFBamRelationByNarrowedIdxKey,
		Map<CFLibDbKeyHash256, ICFBamRelationObj > > indexByNarrowedIdx;
	public static String TABLE_NAME = "Relation";
	public static String TABLE_DBNAME = "reldef";

	public CFBamRelationTableObj() {
		schema = null;
		members = new HashMap<CFLibDbKeyHash256, ICFBamRelationObj>();
		allRelation = null;
		indexByTenantIdx = null;
		indexByUNameIdx = null;
		indexByRelTableIdx = null;
		indexByDefSchemaIdx = null;
		indexByFromKeyIdx = null;
		indexByToTblIdx = null;
		indexByToKeyIdx = null;
		indexByNarrowedIdx = null;
	}

	public CFBamRelationTableObj( ICFBamSchemaObj argSchema ) {
		schema = (ICFBamSchemaObj)argSchema;
		members = new HashMap<CFLibDbKeyHash256, ICFBamRelationObj>();
		allRelation = null;
		indexByTenantIdx = null;
		indexByUNameIdx = null;
		indexByRelTableIdx = null;
		indexByDefSchemaIdx = null;
		indexByFromKeyIdx = null;
		indexByToTblIdx = null;
		indexByToKeyIdx = null;
		indexByNarrowedIdx = null;
	}
	
	/**
	 *	Get class code always returns the runtime class code for the objects, which is not stable until the application is done initializing and registering its objects.
	 *
	 *	@return runtime classcode
	 */ 
	@Override
	public int getClassCode() {
		return CFBamRelationTableObj.getRuntimeClassCode();
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
			throw new CFLibArgumentUnderflowException(CFBamRelationTableObj.class, "setRuntimeClassCode", 1, "argNewClassCode", argNewClassCode, 1);
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
		allRelation = null;
		indexByTenantIdx = null;
		indexByUNameIdx = null;
		indexByRelTableIdx = null;
		indexByDefSchemaIdx = null;
		indexByFromKeyIdx = null;
		indexByToTblIdx = null;
		indexByToKeyIdx = null;
		indexByNarrowedIdx = null;
		List<ICFBamRelationObj> toForget = new LinkedList<ICFBamRelationObj>();
		ICFBamRelationObj cur = null;
		Iterator<ICFBamRelationObj> iter = members.values().iterator();
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
	 *	CFBamRelationObj.
	 */
	@Override
	public ICFBamRelationObj newInstance() {
		ICFBamRelationObj inst = new CFBamRelationObj( schema );
		return( inst );
	}

	/**
	 *	If your implementation subclasses the objects,
	 *	you'll want to overload the constructByClassCode()
	 *	implementation to return your implementation's
	 *	instances instead of the base implementation.
	 *
	 *	This is the sole factory for instances derived from
	 *	CFBamRelationObj.
	 */
	@Override
	public ICFBamRelationEditObj newEditInstance( ICFBamRelationObj orig ) {
		ICFBamRelationEditObj edit = new CFBamRelationEditObj( orig );
		return( edit );
	}

	@Override
	public ICFBamRelationObj realiseRelation( ICFBamRelationObj Obj ) {
		ICFBamRelationObj obj = Obj;
		CFLibDbKeyHash256 pkey = obj.getPKey();
		ICFBamRelationObj keepObj = null;
		if( members.containsKey( pkey ) && ( null != members.get( pkey ) ) ) {
			ICFBamRelationObj existingObj = members.get( pkey );
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
				Map<CFLibDbKeyHash256, ICFBamRelationObj > mapTenantIdx = indexByTenantIdx.get( keyTenantIdx );
				if( mapTenantIdx != null ) {
					indexByTenantIdx.remove( keyTenantIdx );
				}
			}

			if( indexByUNameIdx != null ) {
				ICFBamRelationByUNameIdxKey keyUNameIdx =
					schema.getCFBamBackingStore().getFactoryRelation().newByUNameIdxKey();
				keyUNameIdx.setRequiredTableId( keepObj.getRequiredTableId() );
				keyUNameIdx.setRequiredName( keepObj.getRequiredName() );
				indexByUNameIdx.remove( keyUNameIdx );
			}

			if( indexByRelTableIdx != null ) {
				ICFBamRelationByRelTableIdxKey keyRelTableIdx =
					schema.getCFBamBackingStore().getFactoryRelation().newByRelTableIdxKey();
				keyRelTableIdx.setRequiredTableId( keepObj.getRequiredTableId() );
				Map<CFLibDbKeyHash256, ICFBamRelationObj > mapRelTableIdx = indexByRelTableIdx.get( keyRelTableIdx );
				if( mapRelTableIdx != null ) {
					mapRelTableIdx.remove( keepObj.getPKey() );
					if( mapRelTableIdx.size() <= 0 ) {
						indexByRelTableIdx.remove( keyRelTableIdx );
					}
				}
			}

			if( indexByDefSchemaIdx != null ) {
				ICFBamRelationByDefSchemaIdxKey keyDefSchemaIdx =
					schema.getCFBamBackingStore().getFactoryRelation().newByDefSchemaIdxKey();
				keyDefSchemaIdx.setOptionalDefSchemaId( keepObj.getOptionalDefSchemaId() );
				Map<CFLibDbKeyHash256, ICFBamRelationObj > mapDefSchemaIdx = indexByDefSchemaIdx.get( keyDefSchemaIdx );
				if( mapDefSchemaIdx != null ) {
					mapDefSchemaIdx.remove( keepObj.getPKey() );
					if( mapDefSchemaIdx.size() <= 0 ) {
						indexByDefSchemaIdx.remove( keyDefSchemaIdx );
					}
				}
			}

			if( indexByFromKeyIdx != null ) {
				ICFBamRelationByFromKeyIdxKey keyFromKeyIdx =
					schema.getCFBamBackingStore().getFactoryRelation().newByFromKeyIdxKey();
				keyFromKeyIdx.setRequiredFromIndexId( keepObj.getRequiredFromIndexId() );
				Map<CFLibDbKeyHash256, ICFBamRelationObj > mapFromKeyIdx = indexByFromKeyIdx.get( keyFromKeyIdx );
				if( mapFromKeyIdx != null ) {
					mapFromKeyIdx.remove( keepObj.getPKey() );
					if( mapFromKeyIdx.size() <= 0 ) {
						indexByFromKeyIdx.remove( keyFromKeyIdx );
					}
				}
			}

			if( indexByToTblIdx != null ) {
				ICFBamRelationByToTblIdxKey keyToTblIdx =
					schema.getCFBamBackingStore().getFactoryRelation().newByToTblIdxKey();
				keyToTblIdx.setRequiredToTableId( keepObj.getRequiredToTableId() );
				Map<CFLibDbKeyHash256, ICFBamRelationObj > mapToTblIdx = indexByToTblIdx.get( keyToTblIdx );
				if( mapToTblIdx != null ) {
					mapToTblIdx.remove( keepObj.getPKey() );
					if( mapToTblIdx.size() <= 0 ) {
						indexByToTblIdx.remove( keyToTblIdx );
					}
				}
			}

			if( indexByToKeyIdx != null ) {
				ICFBamRelationByToKeyIdxKey keyToKeyIdx =
					schema.getCFBamBackingStore().getFactoryRelation().newByToKeyIdxKey();
				keyToKeyIdx.setRequiredToIndexId( keepObj.getRequiredToIndexId() );
				Map<CFLibDbKeyHash256, ICFBamRelationObj > mapToKeyIdx = indexByToKeyIdx.get( keyToKeyIdx );
				if( mapToKeyIdx != null ) {
					mapToKeyIdx.remove( keepObj.getPKey() );
					if( mapToKeyIdx.size() <= 0 ) {
						indexByToKeyIdx.remove( keyToKeyIdx );
					}
				}
			}

			if( indexByNarrowedIdx != null ) {
				ICFBamRelationByNarrowedIdxKey keyNarrowedIdx =
					schema.getCFBamBackingStore().getFactoryRelation().newByNarrowedIdxKey();
				keyNarrowedIdx.setOptionalNarrowedId( keepObj.getOptionalNarrowedId() );
				Map<CFLibDbKeyHash256, ICFBamRelationObj > mapNarrowedIdx = indexByNarrowedIdx.get( keyNarrowedIdx );
				if( mapNarrowedIdx != null ) {
					mapNarrowedIdx.remove( keepObj.getPKey() );
					if( mapNarrowedIdx.size() <= 0 ) {
						indexByNarrowedIdx.remove( keyNarrowedIdx );
					}
				}
			}
			// Keep passing the new object because it's the one with the record
			// that the base table needs to copy to the existing object from
			// the cache.
			keepObj = (ICFBamRelationObj)schema.getScopeTableObj().realiseScope( Obj );

			// Attach new object to alternate and duplicate indexes -- PKey stay stable

			if( indexByTenantIdx != null ) {
				ICFBamScopeByTenantIdxKey keyTenantIdx =
					schema.getCFBamBackingStore().getFactoryScope().newByTenantIdxKey();
				keyTenantIdx.setRequiredTenantId( keepObj.getRequiredTenantId() );
				Map<CFLibDbKeyHash256, ICFBamRelationObj > mapTenantIdx = indexByTenantIdx.get( keyTenantIdx );
				if( mapTenantIdx != null ) {
					mapTenantIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByUNameIdx != null ) {
				ICFBamRelationByUNameIdxKey keyUNameIdx =
					schema.getCFBamBackingStore().getFactoryRelation().newByUNameIdxKey();
				keyUNameIdx.setRequiredTableId( keepObj.getRequiredTableId() );
				keyUNameIdx.setRequiredName( keepObj.getRequiredName() );
				indexByUNameIdx.put( keyUNameIdx, keepObj );
			}

			if( indexByRelTableIdx != null ) {
				ICFBamRelationByRelTableIdxKey keyRelTableIdx =
					schema.getCFBamBackingStore().getFactoryRelation().newByRelTableIdxKey();
				keyRelTableIdx.setRequiredTableId( keepObj.getRequiredTableId() );
				Map<CFLibDbKeyHash256, ICFBamRelationObj > mapRelTableIdx = indexByRelTableIdx.get( keyRelTableIdx );
				if( mapRelTableIdx != null ) {
					mapRelTableIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByDefSchemaIdx != null ) {
				ICFBamRelationByDefSchemaIdxKey keyDefSchemaIdx =
					schema.getCFBamBackingStore().getFactoryRelation().newByDefSchemaIdxKey();
				keyDefSchemaIdx.setOptionalDefSchemaId( keepObj.getOptionalDefSchemaId() );
				Map<CFLibDbKeyHash256, ICFBamRelationObj > mapDefSchemaIdx = indexByDefSchemaIdx.get( keyDefSchemaIdx );
				if( mapDefSchemaIdx != null ) {
					mapDefSchemaIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByFromKeyIdx != null ) {
				ICFBamRelationByFromKeyIdxKey keyFromKeyIdx =
					schema.getCFBamBackingStore().getFactoryRelation().newByFromKeyIdxKey();
				keyFromKeyIdx.setRequiredFromIndexId( keepObj.getRequiredFromIndexId() );
				Map<CFLibDbKeyHash256, ICFBamRelationObj > mapFromKeyIdx = indexByFromKeyIdx.get( keyFromKeyIdx );
				if( mapFromKeyIdx != null ) {
					mapFromKeyIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByToTblIdx != null ) {
				ICFBamRelationByToTblIdxKey keyToTblIdx =
					schema.getCFBamBackingStore().getFactoryRelation().newByToTblIdxKey();
				keyToTblIdx.setRequiredToTableId( keepObj.getRequiredToTableId() );
				Map<CFLibDbKeyHash256, ICFBamRelationObj > mapToTblIdx = indexByToTblIdx.get( keyToTblIdx );
				if( mapToTblIdx != null ) {
					mapToTblIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByToKeyIdx != null ) {
				ICFBamRelationByToKeyIdxKey keyToKeyIdx =
					schema.getCFBamBackingStore().getFactoryRelation().newByToKeyIdxKey();
				keyToKeyIdx.setRequiredToIndexId( keepObj.getRequiredToIndexId() );
				Map<CFLibDbKeyHash256, ICFBamRelationObj > mapToKeyIdx = indexByToKeyIdx.get( keyToKeyIdx );
				if( mapToKeyIdx != null ) {
					mapToKeyIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByNarrowedIdx != null ) {
				ICFBamRelationByNarrowedIdxKey keyNarrowedIdx =
					schema.getCFBamBackingStore().getFactoryRelation().newByNarrowedIdxKey();
				keyNarrowedIdx.setOptionalNarrowedId( keepObj.getOptionalNarrowedId() );
				Map<CFLibDbKeyHash256, ICFBamRelationObj > mapNarrowedIdx = indexByNarrowedIdx.get( keyNarrowedIdx );
				if( mapNarrowedIdx != null ) {
					mapNarrowedIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( allRelation != null ) {
				allRelation.put( keepObj.getPKey(), keepObj );
			}
		}
		else {
			keepObj = obj;
			keepObj = (ICFBamRelationObj)schema.getScopeTableObj().realiseScope( keepObj );

			// Attach new object to PKey, all, alternate, and duplicate indexes
			members.put( keepObj.getPKey(), keepObj );
			if( allRelation != null ) {
				allRelation.put( keepObj.getPKey(), keepObj );
			}

			if( indexByTenantIdx != null ) {
				ICFBamScopeByTenantIdxKey keyTenantIdx =
					schema.getCFBamBackingStore().getFactoryScope().newByTenantIdxKey();
				keyTenantIdx.setRequiredTenantId( keepObj.getRequiredTenantId() );
				Map<CFLibDbKeyHash256, ICFBamRelationObj > mapTenantIdx = indexByTenantIdx.get( keyTenantIdx );
				if( mapTenantIdx != null ) {
					mapTenantIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByUNameIdx != null ) {
				ICFBamRelationByUNameIdxKey keyUNameIdx =
					schema.getCFBamBackingStore().getFactoryRelation().newByUNameIdxKey();
				keyUNameIdx.setRequiredTableId( keepObj.getRequiredTableId() );
				keyUNameIdx.setRequiredName( keepObj.getRequiredName() );
				indexByUNameIdx.put( keyUNameIdx, keepObj );
			}

			if( indexByRelTableIdx != null ) {
				ICFBamRelationByRelTableIdxKey keyRelTableIdx =
					schema.getCFBamBackingStore().getFactoryRelation().newByRelTableIdxKey();
				keyRelTableIdx.setRequiredTableId( keepObj.getRequiredTableId() );
				Map<CFLibDbKeyHash256, ICFBamRelationObj > mapRelTableIdx = indexByRelTableIdx.get( keyRelTableIdx );
				if( mapRelTableIdx != null ) {
					mapRelTableIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByDefSchemaIdx != null ) {
				ICFBamRelationByDefSchemaIdxKey keyDefSchemaIdx =
					schema.getCFBamBackingStore().getFactoryRelation().newByDefSchemaIdxKey();
				keyDefSchemaIdx.setOptionalDefSchemaId( keepObj.getOptionalDefSchemaId() );
				Map<CFLibDbKeyHash256, ICFBamRelationObj > mapDefSchemaIdx = indexByDefSchemaIdx.get( keyDefSchemaIdx );
				if( mapDefSchemaIdx != null ) {
					mapDefSchemaIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByFromKeyIdx != null ) {
				ICFBamRelationByFromKeyIdxKey keyFromKeyIdx =
					schema.getCFBamBackingStore().getFactoryRelation().newByFromKeyIdxKey();
				keyFromKeyIdx.setRequiredFromIndexId( keepObj.getRequiredFromIndexId() );
				Map<CFLibDbKeyHash256, ICFBamRelationObj > mapFromKeyIdx = indexByFromKeyIdx.get( keyFromKeyIdx );
				if( mapFromKeyIdx != null ) {
					mapFromKeyIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByToTblIdx != null ) {
				ICFBamRelationByToTblIdxKey keyToTblIdx =
					schema.getCFBamBackingStore().getFactoryRelation().newByToTblIdxKey();
				keyToTblIdx.setRequiredToTableId( keepObj.getRequiredToTableId() );
				Map<CFLibDbKeyHash256, ICFBamRelationObj > mapToTblIdx = indexByToTblIdx.get( keyToTblIdx );
				if( mapToTblIdx != null ) {
					mapToTblIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByToKeyIdx != null ) {
				ICFBamRelationByToKeyIdxKey keyToKeyIdx =
					schema.getCFBamBackingStore().getFactoryRelation().newByToKeyIdxKey();
				keyToKeyIdx.setRequiredToIndexId( keepObj.getRequiredToIndexId() );
				Map<CFLibDbKeyHash256, ICFBamRelationObj > mapToKeyIdx = indexByToKeyIdx.get( keyToKeyIdx );
				if( mapToKeyIdx != null ) {
					mapToKeyIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByNarrowedIdx != null ) {
				ICFBamRelationByNarrowedIdxKey keyNarrowedIdx =
					schema.getCFBamBackingStore().getFactoryRelation().newByNarrowedIdxKey();
				keyNarrowedIdx.setOptionalNarrowedId( keepObj.getOptionalNarrowedId() );
				Map<CFLibDbKeyHash256, ICFBamRelationObj > mapNarrowedIdx = indexByNarrowedIdx.get( keyNarrowedIdx );
				if( mapNarrowedIdx != null ) {
					mapNarrowedIdx.put( keepObj.getPKey(), keepObj );
				}
			}

		}
		return( keepObj );
	}

	@Override
	public ICFBamRelationObj createRelation( ICFBamRelationObj Obj ) {
		ICFBamRelationObj obj = Obj;
		ICFBamRelation rec = obj.getRelationRec();
		schema.getCFBamBackingStore().getTableRelation().createRelation(
			null,
			rec );
		obj.copyRecToPKey();
		if( obj.getClassCode() == runtimeClassCode ) {
			obj = (ICFBamRelationObj)(obj.realise());
		}
		obj.endEdit();
		return( obj );
	}

	@Override
	public ICFBamRelationObj readRelation( CFLibDbKeyHash256 pkey ) {
		return( readRelation( pkey, false ) );
	}

	@Override
	public ICFBamRelationObj readRelation( CFLibDbKeyHash256 pkey, boolean forceRead ) {
		ICFBamRelationObj obj = null;
		if( ( ! forceRead ) && members.containsKey( pkey ) ) {
			obj = members.get( pkey );
		}
		else {
			ICFBamRelation readRec = schema.getCFBamBackingStore().getTableRelation().readDerivedByIdIdx( null,
						pkey );
			if( readRec != null ) {
				obj = (ICFBamRelationObj)schema.getScopeTableObj().constructByClassCode( readRec.getClassCode() );
				obj.setPKey( readRec.getPKey() );
				obj.setRec( readRec );
				obj = (ICFBamRelationObj)obj.realise();
			}
		}
		return( obj );
	}

	@Override
	public ICFBamRelationObj readCachedRelation( CFLibDbKeyHash256 pkey ) {
		ICFBamRelationObj obj = null;
		if( members.containsKey( pkey ) ) {
			obj = members.get( pkey );
		}
		return( obj );
	}

	@Override
	public void reallyDeepDisposeRelation( ICFBamRelationObj obj )
	{
		final String S_ProcName = "CFBamRelationTableObj.reallyDeepDisposeRelation() ";
		String classCode;
		if( obj == null ) {
			return;
		}
		CFLibDbKeyHash256 pkey = obj.getPKey();
		ICFBamRelationObj existing = readCachedRelation( pkey );
		if( existing == null ) {
			return;
		}
		members.remove( pkey );
		ICFBamRelationByUNameIdxKey keyUNameIdx = schema.getCFBamBackingStore().getFactoryRelation().newByUNameIdxKey();
		keyUNameIdx.setRequiredTableId( existing.getRequiredTableId() );
		keyUNameIdx.setRequiredName( existing.getRequiredName() );

		ICFBamRelationByRelTableIdxKey keyRelTableIdx = schema.getCFBamBackingStore().getFactoryRelation().newByRelTableIdxKey();
		keyRelTableIdx.setRequiredTableId( existing.getRequiredTableId() );

		ICFBamRelationByDefSchemaIdxKey keyDefSchemaIdx = schema.getCFBamBackingStore().getFactoryRelation().newByDefSchemaIdxKey();
		keyDefSchemaIdx.setOptionalDefSchemaId( existing.getOptionalDefSchemaId() );

		ICFBamRelationByFromKeyIdxKey keyFromKeyIdx = schema.getCFBamBackingStore().getFactoryRelation().newByFromKeyIdxKey();
		keyFromKeyIdx.setRequiredFromIndexId( existing.getRequiredFromIndexId() );

		ICFBamRelationByToTblIdxKey keyToTblIdx = schema.getCFBamBackingStore().getFactoryRelation().newByToTblIdxKey();
		keyToTblIdx.setRequiredToTableId( existing.getRequiredToTableId() );

		ICFBamRelationByToKeyIdxKey keyToKeyIdx = schema.getCFBamBackingStore().getFactoryRelation().newByToKeyIdxKey();
		keyToKeyIdx.setRequiredToIndexId( existing.getRequiredToIndexId() );

		ICFBamRelationByNarrowedIdxKey keyNarrowedIdx = schema.getCFBamBackingStore().getFactoryRelation().newByNarrowedIdxKey();
		keyNarrowedIdx.setOptionalNarrowedId( existing.getOptionalNarrowedId() );


					schema.getPopTopDepTableObj().deepDisposePopTopDepByContRelIdx( existing.getRequiredId() );
					schema.getRelationColTableObj().deepDisposeRelationColByRelationIdx( existing.getRequiredId() );

		if( indexByUNameIdx != null ) {
			indexByUNameIdx.remove( keyUNameIdx );
		}

		if( indexByRelTableIdx != null ) {
			if( indexByRelTableIdx.containsKey( keyRelTableIdx ) ) {
				indexByRelTableIdx.get( keyRelTableIdx ).remove( pkey );
				if( indexByRelTableIdx.get( keyRelTableIdx ).size() <= 0 ) {
					indexByRelTableIdx.remove( keyRelTableIdx );
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

		if( indexByFromKeyIdx != null ) {
			if( indexByFromKeyIdx.containsKey( keyFromKeyIdx ) ) {
				indexByFromKeyIdx.get( keyFromKeyIdx ).remove( pkey );
				if( indexByFromKeyIdx.get( keyFromKeyIdx ).size() <= 0 ) {
					indexByFromKeyIdx.remove( keyFromKeyIdx );
				}
			}
		}

		if( indexByToTblIdx != null ) {
			if( indexByToTblIdx.containsKey( keyToTblIdx ) ) {
				indexByToTblIdx.get( keyToTblIdx ).remove( pkey );
				if( indexByToTblIdx.get( keyToTblIdx ).size() <= 0 ) {
					indexByToTblIdx.remove( keyToTblIdx );
				}
			}
		}

		if( indexByToKeyIdx != null ) {
			if( indexByToKeyIdx.containsKey( keyToKeyIdx ) ) {
				indexByToKeyIdx.get( keyToKeyIdx ).remove( pkey );
				if( indexByToKeyIdx.get( keyToKeyIdx ).size() <= 0 ) {
					indexByToKeyIdx.remove( keyToKeyIdx );
				}
			}
		}

		if( indexByNarrowedIdx != null ) {
			if( indexByNarrowedIdx.containsKey( keyNarrowedIdx ) ) {
				indexByNarrowedIdx.get( keyNarrowedIdx ).remove( pkey );
				if( indexByNarrowedIdx.get( keyNarrowedIdx ).size() <= 0 ) {
					indexByNarrowedIdx.remove( keyNarrowedIdx );
				}
			}
		}


		schema.getScopeTableObj().reallyDeepDisposeScope( obj );
	}
	@Override
	public void deepDisposeRelation( CFLibDbKeyHash256 pkey ) {
		ICFBamRelationObj obj = readCachedRelation( pkey );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public ICFBamRelationObj lockRelation( CFLibDbKeyHash256 pkey ) {
		ICFBamRelationObj locked = null;
		ICFBamRelation lockRec = schema.getCFBamBackingStore().getTableRelation().lockDerived( null, pkey );
		if( lockRec != null ) {
				locked = (ICFBamRelationObj)schema.getScopeTableObj().constructByClassCode( lockRec.getClassCode() );
			locked.setRec( lockRec );
			locked.setPKey( lockRec.getPKey() );
			locked = (ICFBamRelationObj)locked.realise();
		}
		else {
			throw new CFLibCollisionDetectedException( getClass(), "lockRelation", pkey );
		}
		return( locked );
	}

	@Override
	public List<ICFBamRelationObj> readAllRelation() {
		return( readAllRelation( false ) );
	}

	@Override
	public List<ICFBamRelationObj> readAllRelation( boolean forceRead ) {
		final String S_ProcName = "readAllRelation";
		if( ( allRelation == null ) || forceRead ) {
			Map<CFLibDbKeyHash256, ICFBamRelationObj> map = new HashMap<CFLibDbKeyHash256,ICFBamRelationObj>();
			allRelation = map;
			ICFBamRelation[] recList = schema.getCFBamBackingStore().getTableRelation().readAllDerived( null );
			ICFBamRelation rec;
			ICFBamRelationObj obj;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamRelationObj)schema.getScopeTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamRelationObj realised = (ICFBamRelationObj)obj.realise();
			}
		}
		int len = allRelation.size();
		ICFBamRelationObj arr[] = new ICFBamRelationObj[len];
		Iterator<ICFBamRelationObj> valIter = allRelation.values().iterator();
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
		ArrayList<ICFBamRelationObj> arrayList = new ArrayList<ICFBamRelationObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamRelationObj> cmp = new Comparator<ICFBamRelationObj>() {
			@Override
			public int compare( ICFBamRelationObj lhs, ICFBamRelationObj rhs ) {
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
		List<ICFBamRelationObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFBamRelationObj> readCachedAllRelation() {
		final String S_ProcName = "readCachedAllRelation";
		ArrayList<ICFBamRelationObj> arrayList = new ArrayList<ICFBamRelationObj>();
		if( allRelation != null ) {
			int len = allRelation.size();
			ICFBamRelationObj arr[] = new ICFBamRelationObj[len];
			Iterator<ICFBamRelationObj> valIter = allRelation.values().iterator();
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
		Comparator<ICFBamRelationObj> cmp = new Comparator<ICFBamRelationObj>() {
			public int compare( ICFBamRelationObj lhs, ICFBamRelationObj rhs ) {
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
	public ICFBamRelationObj readRelationByIdIdx( CFLibDbKeyHash256 Id )
	{
		return( readRelationByIdIdx( Id,
			false ) );
	}

	@Override
	public ICFBamRelationObj readRelationByIdIdx( CFLibDbKeyHash256 Id, boolean forceRead )
	{
		ICFBamRelationObj obj = readRelation( Id, forceRead );
		return( obj );
	}

	@Override
	public List<ICFBamRelationObj> readRelationByTenantIdx( CFLibDbKeyHash256 TenantId )
	{
		return( readRelationByTenantIdx( TenantId,
			false ) );
	}

	@Override
	public List<ICFBamRelationObj> readRelationByTenantIdx( CFLibDbKeyHash256 TenantId,
		boolean forceRead )
	{
		final String S_ProcName = "readRelationByTenantIdx";
		ICFBamScopeByTenantIdxKey key = schema.getCFBamBackingStore().getFactoryScope().newByTenantIdxKey();
		key.setRequiredTenantId( TenantId );
		Map<CFLibDbKeyHash256, ICFBamRelationObj> dict;
		if( indexByTenantIdx == null ) {
			indexByTenantIdx = new HashMap< ICFBamScopeByTenantIdxKey,
				Map< CFLibDbKeyHash256, ICFBamRelationObj > >();
		}
		if( ( ! forceRead ) && indexByTenantIdx.containsKey( key ) ) {
			dict = indexByTenantIdx.get( key );
		}
		else {
			dict = new HashMap<CFLibDbKeyHash256, ICFBamRelationObj>();
			ICFBamScopeObj obj;
			ICFBamScope[] recList = schema.getCFBamBackingStore().getTableScope().readDerivedByTenantIdx( null,
				TenantId );
			ICFBamScope rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamRelationObj)schema.getScopeTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamRelationObj realised = (ICFBamRelationObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByTenantIdx.put( key, dict );
		}
		int len = dict.size();
		ICFBamRelationObj arr[] = new ICFBamRelationObj[len];
		Iterator<ICFBamRelationObj> valIter = dict.values().iterator();
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
		ArrayList<ICFBamRelationObj> arrayList = new ArrayList<ICFBamRelationObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamRelationObj> cmp = new Comparator<ICFBamRelationObj>() {
			@Override
			public int compare( ICFBamRelationObj lhs, ICFBamRelationObj rhs ) {
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
		List<ICFBamRelationObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public ICFBamRelationObj readRelationByUNameIdx( CFLibDbKeyHash256 TableId,
		String Name )
	{
		return( readRelationByUNameIdx( TableId,
			Name,
			false ) );
	}

	@Override
	public ICFBamRelationObj readRelationByUNameIdx( CFLibDbKeyHash256 TableId,
		String Name, boolean forceRead )
	{
		if( indexByUNameIdx == null ) {
			indexByUNameIdx = new HashMap< ICFBamRelationByUNameIdxKey,
				ICFBamRelationObj >();
		}
		ICFBamRelationByUNameIdxKey key = schema.getCFBamBackingStore().getFactoryRelation().newByUNameIdxKey();
		key.setRequiredTableId( TableId );
		key.setRequiredName( Name );
		ICFBamRelationObj obj = null;
		if( ( ! forceRead ) && indexByUNameIdx.containsKey( key ) ) {
			obj = indexByUNameIdx.get( key );
		}
		else {
			ICFBamRelation rec = schema.getCFBamBackingStore().getTableRelation().readDerivedByUNameIdx( null,
				TableId,
				Name );
			if( rec != null ) {
				obj = (ICFBamRelationObj)schema.getScopeTableObj().constructByClassCode( rec.getClassCode() );
				obj.setRec( rec );
				obj.setPKey( rec.getPKey() );
				obj = (ICFBamRelationObj)obj.realise();
			}
		}
		return( obj );
	}

	@Override
	public List<ICFBamRelationObj> readRelationByRelTableIdx( CFLibDbKeyHash256 TableId )
	{
		return( readRelationByRelTableIdx( TableId,
			false ) );
	}

	@Override
	public List<ICFBamRelationObj> readRelationByRelTableIdx( CFLibDbKeyHash256 TableId,
		boolean forceRead )
	{
		final String S_ProcName = "readRelationByRelTableIdx";
		ICFBamRelationByRelTableIdxKey key = schema.getCFBamBackingStore().getFactoryRelation().newByRelTableIdxKey();
		key.setRequiredTableId( TableId );
		Map<CFLibDbKeyHash256, ICFBamRelationObj> dict;
		if( indexByRelTableIdx == null ) {
			indexByRelTableIdx = new HashMap< ICFBamRelationByRelTableIdxKey,
				Map< CFLibDbKeyHash256, ICFBamRelationObj > >();
		}
		if( ( ! forceRead ) && indexByRelTableIdx.containsKey( key ) ) {
			dict = indexByRelTableIdx.get( key );
		}
		else {
			dict = new HashMap<CFLibDbKeyHash256, ICFBamRelationObj>();
			ICFBamRelationObj obj;
			ICFBamRelation[] recList = schema.getCFBamBackingStore().getTableRelation().readDerivedByRelTableIdx( null,
				TableId );
			ICFBamRelation rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamRelationObj)schema.getScopeTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamRelationObj realised = (ICFBamRelationObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByRelTableIdx.put( key, dict );
		}
		int len = dict.size();
		ICFBamRelationObj arr[] = new ICFBamRelationObj[len];
		Iterator<ICFBamRelationObj> valIter = dict.values().iterator();
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
		ArrayList<ICFBamRelationObj> arrayList = new ArrayList<ICFBamRelationObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamRelationObj> cmp = new Comparator<ICFBamRelationObj>() {
			@Override
			public int compare( ICFBamRelationObj lhs, ICFBamRelationObj rhs ) {
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
		List<ICFBamRelationObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFBamRelationObj> readRelationByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId )
	{
		return( readRelationByDefSchemaIdx( DefSchemaId,
			false ) );
	}

	@Override
	public List<ICFBamRelationObj> readRelationByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId,
		boolean forceRead )
	{
		final String S_ProcName = "readRelationByDefSchemaIdx";
		ICFBamRelationByDefSchemaIdxKey key = schema.getCFBamBackingStore().getFactoryRelation().newByDefSchemaIdxKey();
		key.setOptionalDefSchemaId( DefSchemaId );
		Map<CFLibDbKeyHash256, ICFBamRelationObj> dict;
		if( indexByDefSchemaIdx == null ) {
			indexByDefSchemaIdx = new HashMap< ICFBamRelationByDefSchemaIdxKey,
				Map< CFLibDbKeyHash256, ICFBamRelationObj > >();
		}
		if( ( ! forceRead ) && indexByDefSchemaIdx.containsKey( key ) ) {
			dict = indexByDefSchemaIdx.get( key );
		}
		else {
			dict = new HashMap<CFLibDbKeyHash256, ICFBamRelationObj>();
			ICFBamRelationObj obj;
			ICFBamRelation[] recList = schema.getCFBamBackingStore().getTableRelation().readDerivedByDefSchemaIdx( null,
				DefSchemaId );
			ICFBamRelation rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamRelationObj)schema.getScopeTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamRelationObj realised = (ICFBamRelationObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByDefSchemaIdx.put( key, dict );
		}
		int len = dict.size();
		ICFBamRelationObj arr[] = new ICFBamRelationObj[len];
		Iterator<ICFBamRelationObj> valIter = dict.values().iterator();
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
		ArrayList<ICFBamRelationObj> arrayList = new ArrayList<ICFBamRelationObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamRelationObj> cmp = new Comparator<ICFBamRelationObj>() {
			@Override
			public int compare( ICFBamRelationObj lhs, ICFBamRelationObj rhs ) {
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
		List<ICFBamRelationObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFBamRelationObj> readRelationByFromKeyIdx( CFLibDbKeyHash256 FromIndexId )
	{
		return( readRelationByFromKeyIdx( FromIndexId,
			false ) );
	}

	@Override
	public List<ICFBamRelationObj> readRelationByFromKeyIdx( CFLibDbKeyHash256 FromIndexId,
		boolean forceRead )
	{
		final String S_ProcName = "readRelationByFromKeyIdx";
		ICFBamRelationByFromKeyIdxKey key = schema.getCFBamBackingStore().getFactoryRelation().newByFromKeyIdxKey();
		key.setRequiredFromIndexId( FromIndexId );
		Map<CFLibDbKeyHash256, ICFBamRelationObj> dict;
		if( indexByFromKeyIdx == null ) {
			indexByFromKeyIdx = new HashMap< ICFBamRelationByFromKeyIdxKey,
				Map< CFLibDbKeyHash256, ICFBamRelationObj > >();
		}
		if( ( ! forceRead ) && indexByFromKeyIdx.containsKey( key ) ) {
			dict = indexByFromKeyIdx.get( key );
		}
		else {
			dict = new HashMap<CFLibDbKeyHash256, ICFBamRelationObj>();
			ICFBamRelationObj obj;
			ICFBamRelation[] recList = schema.getCFBamBackingStore().getTableRelation().readDerivedByFromKeyIdx( null,
				FromIndexId );
			ICFBamRelation rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamRelationObj)schema.getScopeTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamRelationObj realised = (ICFBamRelationObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByFromKeyIdx.put( key, dict );
		}
		int len = dict.size();
		ICFBamRelationObj arr[] = new ICFBamRelationObj[len];
		Iterator<ICFBamRelationObj> valIter = dict.values().iterator();
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
		ArrayList<ICFBamRelationObj> arrayList = new ArrayList<ICFBamRelationObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamRelationObj> cmp = new Comparator<ICFBamRelationObj>() {
			@Override
			public int compare( ICFBamRelationObj lhs, ICFBamRelationObj rhs ) {
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
		List<ICFBamRelationObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFBamRelationObj> readRelationByToTblIdx( CFLibDbKeyHash256 ToTableId )
	{
		return( readRelationByToTblIdx( ToTableId,
			false ) );
	}

	@Override
	public List<ICFBamRelationObj> readRelationByToTblIdx( CFLibDbKeyHash256 ToTableId,
		boolean forceRead )
	{
		final String S_ProcName = "readRelationByToTblIdx";
		ICFBamRelationByToTblIdxKey key = schema.getCFBamBackingStore().getFactoryRelation().newByToTblIdxKey();
		key.setRequiredToTableId( ToTableId );
		Map<CFLibDbKeyHash256, ICFBamRelationObj> dict;
		if( indexByToTblIdx == null ) {
			indexByToTblIdx = new HashMap< ICFBamRelationByToTblIdxKey,
				Map< CFLibDbKeyHash256, ICFBamRelationObj > >();
		}
		if( ( ! forceRead ) && indexByToTblIdx.containsKey( key ) ) {
			dict = indexByToTblIdx.get( key );
		}
		else {
			dict = new HashMap<CFLibDbKeyHash256, ICFBamRelationObj>();
			ICFBamRelationObj obj;
			ICFBamRelation[] recList = schema.getCFBamBackingStore().getTableRelation().readDerivedByToTblIdx( null,
				ToTableId );
			ICFBamRelation rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamRelationObj)schema.getScopeTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamRelationObj realised = (ICFBamRelationObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByToTblIdx.put( key, dict );
		}
		int len = dict.size();
		ICFBamRelationObj arr[] = new ICFBamRelationObj[len];
		Iterator<ICFBamRelationObj> valIter = dict.values().iterator();
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
		ArrayList<ICFBamRelationObj> arrayList = new ArrayList<ICFBamRelationObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamRelationObj> cmp = new Comparator<ICFBamRelationObj>() {
			@Override
			public int compare( ICFBamRelationObj lhs, ICFBamRelationObj rhs ) {
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
		List<ICFBamRelationObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFBamRelationObj> readRelationByToKeyIdx( CFLibDbKeyHash256 ToIndexId )
	{
		return( readRelationByToKeyIdx( ToIndexId,
			false ) );
	}

	@Override
	public List<ICFBamRelationObj> readRelationByToKeyIdx( CFLibDbKeyHash256 ToIndexId,
		boolean forceRead )
	{
		final String S_ProcName = "readRelationByToKeyIdx";
		ICFBamRelationByToKeyIdxKey key = schema.getCFBamBackingStore().getFactoryRelation().newByToKeyIdxKey();
		key.setRequiredToIndexId( ToIndexId );
		Map<CFLibDbKeyHash256, ICFBamRelationObj> dict;
		if( indexByToKeyIdx == null ) {
			indexByToKeyIdx = new HashMap< ICFBamRelationByToKeyIdxKey,
				Map< CFLibDbKeyHash256, ICFBamRelationObj > >();
		}
		if( ( ! forceRead ) && indexByToKeyIdx.containsKey( key ) ) {
			dict = indexByToKeyIdx.get( key );
		}
		else {
			dict = new HashMap<CFLibDbKeyHash256, ICFBamRelationObj>();
			ICFBamRelationObj obj;
			ICFBamRelation[] recList = schema.getCFBamBackingStore().getTableRelation().readDerivedByToKeyIdx( null,
				ToIndexId );
			ICFBamRelation rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamRelationObj)schema.getScopeTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamRelationObj realised = (ICFBamRelationObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByToKeyIdx.put( key, dict );
		}
		int len = dict.size();
		ICFBamRelationObj arr[] = new ICFBamRelationObj[len];
		Iterator<ICFBamRelationObj> valIter = dict.values().iterator();
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
		ArrayList<ICFBamRelationObj> arrayList = new ArrayList<ICFBamRelationObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamRelationObj> cmp = new Comparator<ICFBamRelationObj>() {
			@Override
			public int compare( ICFBamRelationObj lhs, ICFBamRelationObj rhs ) {
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
		List<ICFBamRelationObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFBamRelationObj> readRelationByNarrowedIdx( CFLibDbKeyHash256 NarrowedId )
	{
		return( readRelationByNarrowedIdx( NarrowedId,
			false ) );
	}

	@Override
	public List<ICFBamRelationObj> readRelationByNarrowedIdx( CFLibDbKeyHash256 NarrowedId,
		boolean forceRead )
	{
		final String S_ProcName = "readRelationByNarrowedIdx";
		ICFBamRelationByNarrowedIdxKey key = schema.getCFBamBackingStore().getFactoryRelation().newByNarrowedIdxKey();
		key.setOptionalNarrowedId( NarrowedId );
		Map<CFLibDbKeyHash256, ICFBamRelationObj> dict;
		if( indexByNarrowedIdx == null ) {
			indexByNarrowedIdx = new HashMap< ICFBamRelationByNarrowedIdxKey,
				Map< CFLibDbKeyHash256, ICFBamRelationObj > >();
		}
		if( ( ! forceRead ) && indexByNarrowedIdx.containsKey( key ) ) {
			dict = indexByNarrowedIdx.get( key );
		}
		else {
			dict = new HashMap<CFLibDbKeyHash256, ICFBamRelationObj>();
			ICFBamRelationObj obj;
			ICFBamRelation[] recList = schema.getCFBamBackingStore().getTableRelation().readDerivedByNarrowedIdx( null,
				NarrowedId );
			ICFBamRelation rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamRelationObj)schema.getScopeTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamRelationObj realised = (ICFBamRelationObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByNarrowedIdx.put( key, dict );
		}
		int len = dict.size();
		ICFBamRelationObj arr[] = new ICFBamRelationObj[len];
		Iterator<ICFBamRelationObj> valIter = dict.values().iterator();
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
		ArrayList<ICFBamRelationObj> arrayList = new ArrayList<ICFBamRelationObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamRelationObj> cmp = new Comparator<ICFBamRelationObj>() {
			@Override
			public int compare( ICFBamRelationObj lhs, ICFBamRelationObj rhs ) {
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
		List<ICFBamRelationObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public ICFBamRelationObj readCachedRelationByIdIdx( CFLibDbKeyHash256 Id )
	{
		ICFBamRelationObj obj = null;
		obj = readCachedRelation( Id );
		return( obj );
	}

	@Override
	public List<ICFBamRelationObj> readCachedRelationByTenantIdx( CFLibDbKeyHash256 TenantId )
	{
		final String S_ProcName = "readCachedRelationByTenantIdx";
		ICFBamScopeByTenantIdxKey key = schema.getCFBamBackingStore().getFactoryScope().newByTenantIdxKey();
		key.setRequiredTenantId( TenantId );
		ArrayList<ICFBamRelationObj> arrayList = new ArrayList<ICFBamRelationObj>();
		if( indexByTenantIdx != null ) {
			Map<CFLibDbKeyHash256, ICFBamRelationObj> dict;
			if( indexByTenantIdx.containsKey( key ) ) {
				dict = indexByTenantIdx.get( key );
				int len = dict.size();
				ICFBamRelationObj arr[] = new ICFBamRelationObj[len];
				Iterator<ICFBamRelationObj> valIter = dict.values().iterator();
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
			ICFBamRelationObj obj;
			Iterator<ICFBamRelationObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFBamRelationObj> cmp = new Comparator<ICFBamRelationObj>() {
			@Override
			public int compare( ICFBamRelationObj lhs, ICFBamRelationObj rhs ) {
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
	public ICFBamRelationObj readCachedRelationByUNameIdx( CFLibDbKeyHash256 TableId,
		String Name )
	{
		ICFBamRelationObj obj = null;
		ICFBamRelationByUNameIdxKey key = schema.getCFBamBackingStore().getFactoryRelation().newByUNameIdxKey();
		key.setRequiredTableId( TableId );
		key.setRequiredName( Name );
		if( indexByUNameIdx != null ) {
			if( indexByUNameIdx.containsKey( key ) ) {
				obj = indexByUNameIdx.get( key );
			}
			else {
				Iterator<ICFBamRelationObj> valIter = members.values().iterator();
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
			Iterator<ICFBamRelationObj> valIter = members.values().iterator();
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
	public List<ICFBamRelationObj> readCachedRelationByRelTableIdx( CFLibDbKeyHash256 TableId )
	{
		final String S_ProcName = "readCachedRelationByRelTableIdx";
		ICFBamRelationByRelTableIdxKey key = schema.getCFBamBackingStore().getFactoryRelation().newByRelTableIdxKey();
		key.setRequiredTableId( TableId );
		ArrayList<ICFBamRelationObj> arrayList = new ArrayList<ICFBamRelationObj>();
		if( indexByRelTableIdx != null ) {
			Map<CFLibDbKeyHash256, ICFBamRelationObj> dict;
			if( indexByRelTableIdx.containsKey( key ) ) {
				dict = indexByRelTableIdx.get( key );
				int len = dict.size();
				ICFBamRelationObj arr[] = new ICFBamRelationObj[len];
				Iterator<ICFBamRelationObj> valIter = dict.values().iterator();
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
			ICFBamRelationObj obj;
			Iterator<ICFBamRelationObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFBamRelationObj> cmp = new Comparator<ICFBamRelationObj>() {
			@Override
			public int compare( ICFBamRelationObj lhs, ICFBamRelationObj rhs ) {
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
	public List<ICFBamRelationObj> readCachedRelationByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId )
	{
		final String S_ProcName = "readCachedRelationByDefSchemaIdx";
		ICFBamRelationByDefSchemaIdxKey key = schema.getCFBamBackingStore().getFactoryRelation().newByDefSchemaIdxKey();
		key.setOptionalDefSchemaId( DefSchemaId );
		ArrayList<ICFBamRelationObj> arrayList = new ArrayList<ICFBamRelationObj>();
		if( indexByDefSchemaIdx != null ) {
			Map<CFLibDbKeyHash256, ICFBamRelationObj> dict;
			if( indexByDefSchemaIdx.containsKey( key ) ) {
				dict = indexByDefSchemaIdx.get( key );
				int len = dict.size();
				ICFBamRelationObj arr[] = new ICFBamRelationObj[len];
				Iterator<ICFBamRelationObj> valIter = dict.values().iterator();
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
			ICFBamRelationObj obj;
			Iterator<ICFBamRelationObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFBamRelationObj> cmp = new Comparator<ICFBamRelationObj>() {
			@Override
			public int compare( ICFBamRelationObj lhs, ICFBamRelationObj rhs ) {
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
	public List<ICFBamRelationObj> readCachedRelationByFromKeyIdx( CFLibDbKeyHash256 FromIndexId )
	{
		final String S_ProcName = "readCachedRelationByFromKeyIdx";
		ICFBamRelationByFromKeyIdxKey key = schema.getCFBamBackingStore().getFactoryRelation().newByFromKeyIdxKey();
		key.setRequiredFromIndexId( FromIndexId );
		ArrayList<ICFBamRelationObj> arrayList = new ArrayList<ICFBamRelationObj>();
		if( indexByFromKeyIdx != null ) {
			Map<CFLibDbKeyHash256, ICFBamRelationObj> dict;
			if( indexByFromKeyIdx.containsKey( key ) ) {
				dict = indexByFromKeyIdx.get( key );
				int len = dict.size();
				ICFBamRelationObj arr[] = new ICFBamRelationObj[len];
				Iterator<ICFBamRelationObj> valIter = dict.values().iterator();
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
			ICFBamRelationObj obj;
			Iterator<ICFBamRelationObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFBamRelationObj> cmp = new Comparator<ICFBamRelationObj>() {
			@Override
			public int compare( ICFBamRelationObj lhs, ICFBamRelationObj rhs ) {
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
	public List<ICFBamRelationObj> readCachedRelationByToTblIdx( CFLibDbKeyHash256 ToTableId )
	{
		final String S_ProcName = "readCachedRelationByToTblIdx";
		ICFBamRelationByToTblIdxKey key = schema.getCFBamBackingStore().getFactoryRelation().newByToTblIdxKey();
		key.setRequiredToTableId( ToTableId );
		ArrayList<ICFBamRelationObj> arrayList = new ArrayList<ICFBamRelationObj>();
		if( indexByToTblIdx != null ) {
			Map<CFLibDbKeyHash256, ICFBamRelationObj> dict;
			if( indexByToTblIdx.containsKey( key ) ) {
				dict = indexByToTblIdx.get( key );
				int len = dict.size();
				ICFBamRelationObj arr[] = new ICFBamRelationObj[len];
				Iterator<ICFBamRelationObj> valIter = dict.values().iterator();
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
			ICFBamRelationObj obj;
			Iterator<ICFBamRelationObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFBamRelationObj> cmp = new Comparator<ICFBamRelationObj>() {
			@Override
			public int compare( ICFBamRelationObj lhs, ICFBamRelationObj rhs ) {
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
	public List<ICFBamRelationObj> readCachedRelationByToKeyIdx( CFLibDbKeyHash256 ToIndexId )
	{
		final String S_ProcName = "readCachedRelationByToKeyIdx";
		ICFBamRelationByToKeyIdxKey key = schema.getCFBamBackingStore().getFactoryRelation().newByToKeyIdxKey();
		key.setRequiredToIndexId( ToIndexId );
		ArrayList<ICFBamRelationObj> arrayList = new ArrayList<ICFBamRelationObj>();
		if( indexByToKeyIdx != null ) {
			Map<CFLibDbKeyHash256, ICFBamRelationObj> dict;
			if( indexByToKeyIdx.containsKey( key ) ) {
				dict = indexByToKeyIdx.get( key );
				int len = dict.size();
				ICFBamRelationObj arr[] = new ICFBamRelationObj[len];
				Iterator<ICFBamRelationObj> valIter = dict.values().iterator();
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
			ICFBamRelationObj obj;
			Iterator<ICFBamRelationObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFBamRelationObj> cmp = new Comparator<ICFBamRelationObj>() {
			@Override
			public int compare( ICFBamRelationObj lhs, ICFBamRelationObj rhs ) {
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
	public List<ICFBamRelationObj> readCachedRelationByNarrowedIdx( CFLibDbKeyHash256 NarrowedId )
	{
		final String S_ProcName = "readCachedRelationByNarrowedIdx";
		ICFBamRelationByNarrowedIdxKey key = schema.getCFBamBackingStore().getFactoryRelation().newByNarrowedIdxKey();
		key.setOptionalNarrowedId( NarrowedId );
		ArrayList<ICFBamRelationObj> arrayList = new ArrayList<ICFBamRelationObj>();
		if( indexByNarrowedIdx != null ) {
			Map<CFLibDbKeyHash256, ICFBamRelationObj> dict;
			if( indexByNarrowedIdx.containsKey( key ) ) {
				dict = indexByNarrowedIdx.get( key );
				int len = dict.size();
				ICFBamRelationObj arr[] = new ICFBamRelationObj[len];
				Iterator<ICFBamRelationObj> valIter = dict.values().iterator();
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
			ICFBamRelationObj obj;
			Iterator<ICFBamRelationObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFBamRelationObj> cmp = new Comparator<ICFBamRelationObj>() {
			@Override
			public int compare( ICFBamRelationObj lhs, ICFBamRelationObj rhs ) {
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
	public void deepDisposeRelationByIdIdx( CFLibDbKeyHash256 Id )
	{
		ICFBamRelationObj obj = readCachedRelationByIdIdx( Id );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public void deepDisposeRelationByTenantIdx( CFLibDbKeyHash256 TenantId )
	{
		final String S_ProcName = "deepDisposeRelationByTenantIdx";
		ICFBamRelationObj obj;
		List<ICFBamRelationObj> arrayList = readCachedRelationByTenantIdx( TenantId );
		if( arrayList != null )  {
			Iterator<ICFBamRelationObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public void deepDisposeRelationByUNameIdx( CFLibDbKeyHash256 TableId,
		String Name )
	{
		ICFBamRelationObj obj = readCachedRelationByUNameIdx( TableId,
				Name );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public void deepDisposeRelationByRelTableIdx( CFLibDbKeyHash256 TableId )
	{
		final String S_ProcName = "deepDisposeRelationByRelTableIdx";
		ICFBamRelationObj obj;
		List<ICFBamRelationObj> arrayList = readCachedRelationByRelTableIdx( TableId );
		if( arrayList != null )  {
			Iterator<ICFBamRelationObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public void deepDisposeRelationByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId )
	{
		final String S_ProcName = "deepDisposeRelationByDefSchemaIdx";
		ICFBamRelationObj obj;
		List<ICFBamRelationObj> arrayList = readCachedRelationByDefSchemaIdx( DefSchemaId );
		if( arrayList != null )  {
			Iterator<ICFBamRelationObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public void deepDisposeRelationByFromKeyIdx( CFLibDbKeyHash256 FromIndexId )
	{
		final String S_ProcName = "deepDisposeRelationByFromKeyIdx";
		ICFBamRelationObj obj;
		List<ICFBamRelationObj> arrayList = readCachedRelationByFromKeyIdx( FromIndexId );
		if( arrayList != null )  {
			Iterator<ICFBamRelationObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public void deepDisposeRelationByToTblIdx( CFLibDbKeyHash256 ToTableId )
	{
		final String S_ProcName = "deepDisposeRelationByToTblIdx";
		ICFBamRelationObj obj;
		List<ICFBamRelationObj> arrayList = readCachedRelationByToTblIdx( ToTableId );
		if( arrayList != null )  {
			Iterator<ICFBamRelationObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public void deepDisposeRelationByToKeyIdx( CFLibDbKeyHash256 ToIndexId )
	{
		final String S_ProcName = "deepDisposeRelationByToKeyIdx";
		ICFBamRelationObj obj;
		List<ICFBamRelationObj> arrayList = readCachedRelationByToKeyIdx( ToIndexId );
		if( arrayList != null )  {
			Iterator<ICFBamRelationObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public void deepDisposeRelationByNarrowedIdx( CFLibDbKeyHash256 NarrowedId )
	{
		final String S_ProcName = "deepDisposeRelationByNarrowedIdx";
		ICFBamRelationObj obj;
		List<ICFBamRelationObj> arrayList = readCachedRelationByNarrowedIdx( NarrowedId );
		if( arrayList != null )  {
			Iterator<ICFBamRelationObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public ICFBamRelationObj updateRelation( ICFBamRelationObj Obj ) {
		ICFBamRelationObj obj = Obj;
		schema.getCFBamBackingStore().getTableRelation().updateRelation( null,
			Obj.getRelationRec() );
		if( Obj.getClassCode() == ((ICFBamSchemaObj)getSchema()).getRelationTableObj().getClassCode() ) {
			obj = (ICFBamRelationObj)Obj.realise();
		}
		obj.endEdit();
		return( obj );
	}

	@Override
	public void deleteRelation( ICFBamRelationObj Obj ) {
		ICFBamRelationObj obj = Obj;
		schema.getCFBamBackingStore().getTableRelation().deleteRelation( null,
			obj.getRelationRec() );
		Obj.forget();
	}

	@Override
	public void deleteRelationByIdIdx( CFLibDbKeyHash256 Id )
	{
		ICFBamRelationObj obj = readRelation(Id);
		if( obj != null ) {
			ICFBamRelationEditObj editObj = (ICFBamRelationEditObj)obj.getEdit();
			boolean editStarted;
			if( editObj == null ) {
				editObj = (ICFBamRelationEditObj)obj.beginEdit();
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
		deepDisposeRelationByIdIdx( Id );
	}

	@Override
	public void deleteRelationByTenantIdx( CFLibDbKeyHash256 TenantId )
	{
		ICFBamScopeByTenantIdxKey key = schema.getCFBamBackingStore().getFactoryScope().newByTenantIdxKey();
		key.setRequiredTenantId( TenantId );
		if( indexByTenantIdx == null ) {
			indexByTenantIdx = new HashMap< ICFBamScopeByTenantIdxKey,
				Map< CFLibDbKeyHash256, ICFBamRelationObj > >();
		}
		if( indexByTenantIdx.containsKey( key ) ) {
			Map<CFLibDbKeyHash256, ICFBamRelationObj> dict = indexByTenantIdx.get( key );
			schema.getCFBamBackingStore().getTableRelation().deleteRelationByTenantIdx( null,
				TenantId );
			Iterator<ICFBamRelationObj> iter = dict.values().iterator();
			ICFBamRelationObj obj;
			List<ICFBamRelationObj> toForget = new LinkedList<ICFBamRelationObj>();
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
			schema.getCFBamBackingStore().getTableRelation().deleteRelationByTenantIdx( null,
				TenantId );
		}
		deepDisposeRelationByTenantIdx( TenantId );
	}

	@Override
	public void deleteRelationByUNameIdx( CFLibDbKeyHash256 TableId,
		String Name )
	{
		if( indexByUNameIdx == null ) {
			indexByUNameIdx = new HashMap< ICFBamRelationByUNameIdxKey,
				ICFBamRelationObj >();
		}
		ICFBamRelationByUNameIdxKey key = schema.getCFBamBackingStore().getFactoryRelation().newByUNameIdxKey();
		key.setRequiredTableId( TableId );
		key.setRequiredName( Name );
		ICFBamRelationObj obj = null;
		if( indexByUNameIdx.containsKey( key ) ) {
			obj = indexByUNameIdx.get( key );
			schema.getCFBamBackingStore().getTableRelation().deleteRelationByUNameIdx( null,
				TableId,
				Name );
			obj.forget();
		}
		else {
			schema.getCFBamBackingStore().getTableRelation().deleteRelationByUNameIdx( null,
				TableId,
				Name );
		}
		deepDisposeRelationByUNameIdx( TableId,
				Name );
	}

	@Override
	public void deleteRelationByRelTableIdx( CFLibDbKeyHash256 TableId )
	{
		ICFBamRelationByRelTableIdxKey key = schema.getCFBamBackingStore().getFactoryRelation().newByRelTableIdxKey();
		key.setRequiredTableId( TableId );
		if( indexByRelTableIdx == null ) {
			indexByRelTableIdx = new HashMap< ICFBamRelationByRelTableIdxKey,
				Map< CFLibDbKeyHash256, ICFBamRelationObj > >();
		}
		if( indexByRelTableIdx.containsKey( key ) ) {
			Map<CFLibDbKeyHash256, ICFBamRelationObj> dict = indexByRelTableIdx.get( key );
			schema.getCFBamBackingStore().getTableRelation().deleteRelationByRelTableIdx( null,
				TableId );
			Iterator<ICFBamRelationObj> iter = dict.values().iterator();
			ICFBamRelationObj obj;
			List<ICFBamRelationObj> toForget = new LinkedList<ICFBamRelationObj>();
			while( iter.hasNext() ) {
				obj = iter.next();
				toForget.add( obj );
			}
			iter = toForget.iterator();
			while( iter.hasNext() ) {
				obj = iter.next();
				obj.forget();
			}
			indexByRelTableIdx.remove( key );
		}
		else {
			schema.getCFBamBackingStore().getTableRelation().deleteRelationByRelTableIdx( null,
				TableId );
		}
		deepDisposeRelationByRelTableIdx( TableId );
	}

	@Override
	public void deleteRelationByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId )
	{
		ICFBamRelationByDefSchemaIdxKey key = schema.getCFBamBackingStore().getFactoryRelation().newByDefSchemaIdxKey();
		key.setOptionalDefSchemaId( DefSchemaId );
		if( indexByDefSchemaIdx == null ) {
			indexByDefSchemaIdx = new HashMap< ICFBamRelationByDefSchemaIdxKey,
				Map< CFLibDbKeyHash256, ICFBamRelationObj > >();
		}
		if( indexByDefSchemaIdx.containsKey( key ) ) {
			Map<CFLibDbKeyHash256, ICFBamRelationObj> dict = indexByDefSchemaIdx.get( key );
			schema.getCFBamBackingStore().getTableRelation().deleteRelationByDefSchemaIdx( null,
				DefSchemaId );
			Iterator<ICFBamRelationObj> iter = dict.values().iterator();
			ICFBamRelationObj obj;
			List<ICFBamRelationObj> toForget = new LinkedList<ICFBamRelationObj>();
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
			schema.getCFBamBackingStore().getTableRelation().deleteRelationByDefSchemaIdx( null,
				DefSchemaId );
		}
		deepDisposeRelationByDefSchemaIdx( DefSchemaId );
	}

	@Override
	public void deleteRelationByFromKeyIdx( CFLibDbKeyHash256 FromIndexId )
	{
		ICFBamRelationByFromKeyIdxKey key = schema.getCFBamBackingStore().getFactoryRelation().newByFromKeyIdxKey();
		key.setRequiredFromIndexId( FromIndexId );
		if( indexByFromKeyIdx == null ) {
			indexByFromKeyIdx = new HashMap< ICFBamRelationByFromKeyIdxKey,
				Map< CFLibDbKeyHash256, ICFBamRelationObj > >();
		}
		if( indexByFromKeyIdx.containsKey( key ) ) {
			Map<CFLibDbKeyHash256, ICFBamRelationObj> dict = indexByFromKeyIdx.get( key );
			schema.getCFBamBackingStore().getTableRelation().deleteRelationByFromKeyIdx( null,
				FromIndexId );
			Iterator<ICFBamRelationObj> iter = dict.values().iterator();
			ICFBamRelationObj obj;
			List<ICFBamRelationObj> toForget = new LinkedList<ICFBamRelationObj>();
			while( iter.hasNext() ) {
				obj = iter.next();
				toForget.add( obj );
			}
			iter = toForget.iterator();
			while( iter.hasNext() ) {
				obj = iter.next();
				obj.forget();
			}
			indexByFromKeyIdx.remove( key );
		}
		else {
			schema.getCFBamBackingStore().getTableRelation().deleteRelationByFromKeyIdx( null,
				FromIndexId );
		}
		deepDisposeRelationByFromKeyIdx( FromIndexId );
	}

	@Override
	public void deleteRelationByToTblIdx( CFLibDbKeyHash256 ToTableId )
	{
		ICFBamRelationByToTblIdxKey key = schema.getCFBamBackingStore().getFactoryRelation().newByToTblIdxKey();
		key.setRequiredToTableId( ToTableId );
		if( indexByToTblIdx == null ) {
			indexByToTblIdx = new HashMap< ICFBamRelationByToTblIdxKey,
				Map< CFLibDbKeyHash256, ICFBamRelationObj > >();
		}
		if( indexByToTblIdx.containsKey( key ) ) {
			Map<CFLibDbKeyHash256, ICFBamRelationObj> dict = indexByToTblIdx.get( key );
			schema.getCFBamBackingStore().getTableRelation().deleteRelationByToTblIdx( null,
				ToTableId );
			Iterator<ICFBamRelationObj> iter = dict.values().iterator();
			ICFBamRelationObj obj;
			List<ICFBamRelationObj> toForget = new LinkedList<ICFBamRelationObj>();
			while( iter.hasNext() ) {
				obj = iter.next();
				toForget.add( obj );
			}
			iter = toForget.iterator();
			while( iter.hasNext() ) {
				obj = iter.next();
				obj.forget();
			}
			indexByToTblIdx.remove( key );
		}
		else {
			schema.getCFBamBackingStore().getTableRelation().deleteRelationByToTblIdx( null,
				ToTableId );
		}
		deepDisposeRelationByToTblIdx( ToTableId );
	}

	@Override
	public void deleteRelationByToKeyIdx( CFLibDbKeyHash256 ToIndexId )
	{
		ICFBamRelationByToKeyIdxKey key = schema.getCFBamBackingStore().getFactoryRelation().newByToKeyIdxKey();
		key.setRequiredToIndexId( ToIndexId );
		if( indexByToKeyIdx == null ) {
			indexByToKeyIdx = new HashMap< ICFBamRelationByToKeyIdxKey,
				Map< CFLibDbKeyHash256, ICFBamRelationObj > >();
		}
		if( indexByToKeyIdx.containsKey( key ) ) {
			Map<CFLibDbKeyHash256, ICFBamRelationObj> dict = indexByToKeyIdx.get( key );
			schema.getCFBamBackingStore().getTableRelation().deleteRelationByToKeyIdx( null,
				ToIndexId );
			Iterator<ICFBamRelationObj> iter = dict.values().iterator();
			ICFBamRelationObj obj;
			List<ICFBamRelationObj> toForget = new LinkedList<ICFBamRelationObj>();
			while( iter.hasNext() ) {
				obj = iter.next();
				toForget.add( obj );
			}
			iter = toForget.iterator();
			while( iter.hasNext() ) {
				obj = iter.next();
				obj.forget();
			}
			indexByToKeyIdx.remove( key );
		}
		else {
			schema.getCFBamBackingStore().getTableRelation().deleteRelationByToKeyIdx( null,
				ToIndexId );
		}
		deepDisposeRelationByToKeyIdx( ToIndexId );
	}

	@Override
	public void deleteRelationByNarrowedIdx( CFLibDbKeyHash256 NarrowedId )
	{
		ICFBamRelationByNarrowedIdxKey key = schema.getCFBamBackingStore().getFactoryRelation().newByNarrowedIdxKey();
		key.setOptionalNarrowedId( NarrowedId );
		if( indexByNarrowedIdx == null ) {
			indexByNarrowedIdx = new HashMap< ICFBamRelationByNarrowedIdxKey,
				Map< CFLibDbKeyHash256, ICFBamRelationObj > >();
		}
		if( indexByNarrowedIdx.containsKey( key ) ) {
			Map<CFLibDbKeyHash256, ICFBamRelationObj> dict = indexByNarrowedIdx.get( key );
			schema.getCFBamBackingStore().getTableRelation().deleteRelationByNarrowedIdx( null,
				NarrowedId );
			Iterator<ICFBamRelationObj> iter = dict.values().iterator();
			ICFBamRelationObj obj;
			List<ICFBamRelationObj> toForget = new LinkedList<ICFBamRelationObj>();
			while( iter.hasNext() ) {
				obj = iter.next();
				toForget.add( obj );
			}
			iter = toForget.iterator();
			while( iter.hasNext() ) {
				obj = iter.next();
				obj.forget();
			}
			indexByNarrowedIdx.remove( key );
		}
		else {
			schema.getCFBamBackingStore().getTableRelation().deleteRelationByNarrowedIdx( null,
				NarrowedId );
		}
		deepDisposeRelationByNarrowedIdx( NarrowedId );
	}
}